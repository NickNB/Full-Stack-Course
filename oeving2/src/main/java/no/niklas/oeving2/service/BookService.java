package no.niklas.oeving2.service;

import no.niklas.oeving2.dao.AuthorDao;
import no.niklas.oeving2.dao.BookDao;
import no.niklas.oeving2.model.Author;
import no.niklas.oeving2.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;

    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    public Book getBook(long id) {
        return bookDao.getBookById(id);
    }

    public Book createBook(Book book) {
        if(bookDao.getBooks().stream().anyMatch(book1 -> book.getTitle().equals(book1.getTitle()))) return null;
        if(book.getId() == 0) book.setId(Math.abs(new Random().nextLong()));

        bookDao.addBook(book);
        verifyAuthors(book);
        addBookToAuthors(book);
        return book;
    }

    public String updateBook(long id, Book book) {
        Book oldBook = bookDao.getBookById(id);
        removeBookFromAuthors(oldBook);

        bookDao.updateBook(id, book);
        verifyAuthors(book);
        addBookToAuthors(book);
        return "Updated Book";
    }

    public String deleteBook(long id) {
        Book book = bookDao.getBookById(id);
        removeBookFromAuthors(book);

        bookDao.deleteBook(id);
        return "Deleted Book";
    }


    public void addBookToAuthors(Book book) {
        book.getAuthorIds().forEach(authorId -> {
            Author author = authorDao.getAuthorById(authorId);
            if(author != null) {
                ArrayList<Long> bookIds = author.getBookIds();
                if(!bookIds.contains(book.getId())) bookIds.add(book.getId());
            }
        });
    }

    public void verifyAuthors(Book book) {
        book.getAuthorIds().removeIf(authorId -> {
            Author author = authorDao.getAuthorById(authorId);
            return author == null;
        });
    }

    public void removeBookFromAuthors(Book book) {
        book.getAuthorIds().forEach(authorId -> {
            Author author = authorDao.getAuthorById(authorId);
            if(author != null) {
                ArrayList<Long> bookIds = author.getBookIds();
                bookIds.remove(book.getId());
            }
        });
    }

    public List<Book> search(String search) {
        logger.info("Searched with search word: " + search);

        List<Book> retLs = new ArrayList<>(bookDao.getBooks());
        retLs.removeIf(book ->
                !book.getTitle().toLowerCase().contains(search.toLowerCase()));

        List<Author> authors = new ArrayList<>(authorDao.getAuthors());
        authors.removeIf(author ->
                !author.getName().toLowerCase().contains(search.toLowerCase()));

        authors.forEach(author -> {
            author.getBookIds().forEach(bookId -> {
                Book book = bookDao.getBookById(bookId);
                if(book != null) {
                    if(!retLs.contains(book)) retLs.add(book);
                }
            });
        });

        return retLs;
    }
}

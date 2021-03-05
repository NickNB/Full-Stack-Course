package no.niklas.oeving3.service;

import no.niklas.oeving3.dao.AuthorDao;
import no.niklas.oeving3.dao.BookAuthorConnectionDao;
import no.niklas.oeving3.dao.BookDao;
import no.niklas.oeving3.model.Author;
import no.niklas.oeving3.model.Book;
import no.niklas.oeving3.model.BookAuthorConnection;
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
    private BookAuthorConnectionDao bookAuthorConnectionDao;
    @Autowired
    private AuthorDao authorDao;

    public List<Book> getBooks() {
        List<Book> books = bookDao.getBooks();
        for(Book book : books) {
            ArrayList<BookAuthorConnection> connections = new ArrayList<>(bookAuthorConnectionDao.getConnectionsByBookId(book.getId()));
            book.setConnections(connections);
        }
        return books;
    }

    public Book getBook(long id) {
        Book book = bookDao.getBookById(id);
        ArrayList<BookAuthorConnection> connections = new ArrayList<>(bookAuthorConnectionDao.getConnectionsByBookId(book.getId()));
        book.setConnections(connections);
        return book;
    }

    public Book createBook(Book book) {
        if(bookDao.getBooks().stream().anyMatch(book1 -> book.getTitle().equals(book1.getTitle()))) return null;
        if(book.getId() == 0) book.setId(Math.abs(new Random().nextLong()));

        bookDao.addBook(book);
        bookAuthorConnectionDao.addBookAuthorConnections(book.getConnections());
        return book;
    }

    public String updateBook(long id, Book book) {
        if(book.getId() == 0) book.setId(id);

        bookDao.updateBook(id, book);
        bookAuthorConnectionDao.deleteBookAuthorConnections(bookAuthorConnectionDao.getConnectionsByBookId(id));
        bookAuthorConnectionDao.addBookAuthorConnections(book.getConnections());
        return "Updated Book";
    }

    public String deleteBook(long id) {
        Book book = getBook(id);
        bookDao.deleteBook(id);
        bookAuthorConnectionDao.deleteBookAuthorConnections(book.getConnections());
        return "Deleted Book";
    }

    public List<Book> search(String search) {
        logger.info("Searched for book with word: " + search);

        List<Book> retLs = new ArrayList<>(bookDao.getBooks());
        retLs.removeIf(book ->
                !book.getTitle().toLowerCase().contains(search.toLowerCase()));

        List<Author> authors = new ArrayList<>(authorDao.getAuthors());
        authors.removeIf(author ->
                !author.getName().toLowerCase().contains(search.toLowerCase()));

        authors.forEach(author -> {
            author.getConnections().forEach(connection -> {
                Book book = bookDao.getBookById(connection.getBookId());
                if(book != null) {
                    if(!retLs.contains(book)) retLs.add(book);
                }
            });
        });

        return retLs;
    }
}

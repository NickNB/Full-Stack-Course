package no.niklas.oeving2.service;

import no.niklas.oeving2.dao.AuthorDao;
import no.niklas.oeving2.dao.BookDao;
import no.niklas.oeving2.model.Author;
import no.niklas.oeving2.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AuthorService {
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;

    public List<Author> getAuthors() {
        return authorDao.getAuthors();
    }

    public Author getAuthor(long id) {
        return authorDao.getAuthorById(id);
    }

    public Author createAuthor(Author author) {
        if(authorDao.getAuthors().stream().anyMatch(author1 -> author1.getName().equals(author.getName()))) return null;
        if(author.getId() == 0) author.setId(Math.abs(new Random().nextLong()));

        authorDao.addAuthor(author);
        addAuthorToBooks(author);
        return author;
    }

    public String updateAuthor(long id, Author author) {
        Author oldAuthor = authorDao.getAuthorById(id);
        removeAuthorFromBooks(oldAuthor);

        authorDao.updateAuthor(id, author);
        addAuthorToBooks(author);
        return "Updated author";
    }

    public String deleteAuthor(long id) {
        removeAuthorFromBooks(authorDao.getAuthorById(id));
        authorDao.deleteAuthor(id);
        return "Deleted author";
    }

    public void addAuthorToBooks(Author author) {
        author.getBookIds().forEach(bookId -> {
            Book book = bookDao.getBookById(bookId);
            if(book != null) {
                ArrayList<Long> authorIds = book.getAuthorIds();
                if(!authorIds.contains(author.getId())) authorIds.add(author.getId());
            }
        });
    }

    public void removeAuthorFromBooks(Author author) {
        author.getBookIds().forEach(bookId -> {
            Book book = bookDao.getBookById(bookId);
            if(book != null) {
                ArrayList<Long> authorIds = book.getAuthorIds();
                authorIds.remove(author.getId());
            }
        });
    }

    //public void updateBook(Book book) {
    //    book.getAuthorIds().removeIf(authorId -> {
    //        Author checkAuthor = authorDao.getAuthorById(authorId);
    //        if(checkAuthor == null) return true;
    //        return !checkAuthor.getBookIds().contains(book.getId());
    //    });
    //}
}

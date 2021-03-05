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
public class AuthorService {
    Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookAuthorConnectionDao bookAuthorConnectionDao;
    @Autowired
    private BookDao bookDao;

    public List<Author> getAuthors() {
        List<Author> authors = authorDao.getAuthors();
        for(Author author : authors) {
            ArrayList<BookAuthorConnection> connections = new ArrayList<>(bookAuthorConnectionDao.getConnectionsByAuthorId(author.getId()));
            author.setConnections(connections);
        }
        return authors;
    }

    public Author getAuthor(long id) {
        Author author = authorDao.getAuthorById(id);
        ArrayList<BookAuthorConnection> connections = new ArrayList<>(bookAuthorConnectionDao.getConnectionsByAuthorId(author.getId()));
        author.setConnections(connections);
        return author;
    }

    public Author createAuthor(Author author) {
        if(authorDao.getAuthors().stream().anyMatch(author1 -> author1.getName().equals(author.getName()))) return null;
        if(author.getId() == 0) author.setId(Math.abs(new Random().nextLong()));

        authorDao.addAuthor(author);
        bookAuthorConnectionDao.addBookAuthorConnections(author.getConnections());
        return author;
    }

    public String updateAuthor(long id, Author author) {
        if(author.getId() == 0) author.setId(id);

        authorDao.updateAuthor(id, author);
        bookAuthorConnectionDao.deleteBookAuthorConnections(bookAuthorConnectionDao.getConnectionsByAuthorId(id));
        bookAuthorConnectionDao.addBookAuthorConnections(author.getConnections());
        return "Updated author";
    }

    public String deleteAuthor(long id) {
        Author author = getAuthor(id);

        authorDao.deleteAuthor(id);
        bookAuthorConnectionDao.deleteBookAuthorConnections(author.getConnections());
        return "Deleted author";
    }

    public List<Author> search(String searchStr) {
        logger.warn("Searched for author with word: " + searchStr);
        
        List<Author> retLs = new ArrayList<>(authorDao.getAuthors());
        retLs.removeIf(author ->
                !author.getName().toLowerCase().contains(searchStr.toLowerCase()));

        return retLs;
    }
}

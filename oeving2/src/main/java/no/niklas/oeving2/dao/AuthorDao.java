package no.niklas.oeving2.dao;

import no.niklas.oeving2.DummyDB;
import no.niklas.oeving2.model.Author;
import no.niklas.oeving2.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDao {
    //Gets all authors from db
    public List<Author> getAuthors() {
        return DummyDB.authors;
    }

    //get author by id from db
    public Author getAuthorById(long id) {
        for (Author author: DummyDB.authors) {
            if(author.getId() == id) return author;
        }
        return null;
    }

    //creates adds new author to db
    public void addAuthor(Author author) {
        DummyDB.authors.add(author);
    }

    //updates author in db
    public void updateAuthor(long id, Author newAuthor) {
        DummyDB.authors.removeIf(author -> id == author.getId());
        if(newAuthor.getId() == 0) newAuthor.setId(id);
        DummyDB.authors.add(newAuthor);
    }

    //deletes author from db
    public void deleteAuthor(long id) {
        DummyDB.authors.removeIf(author -> id == author.getId());
    }
}

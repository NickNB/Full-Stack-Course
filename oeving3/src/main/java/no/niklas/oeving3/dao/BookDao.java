package no.niklas.oeving3.dao;

import no.niklas.oeving3.DummyDB;
import no.niklas.oeving3.model.BACRowMapper;
import no.niklas.oeving3.model.Book;
import no.niklas.oeving3.model.BookAuthorConnection;
import no.niklas.oeving3.model.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //Gets all books from db
    public List<Book> getBooks() {
        String query = "select * from book";
        return jdbcTemplate.query(query, new BookRowMapper());
    }

    //get book by id from db
    public Book getBookById(long id) {
        String query = "select * from book where id = ?";
        return jdbcTemplate.queryForObject(query, new BookRowMapper(), id);
    }

    //creates adds new book to db
    public void addBook(Book book) {
        String query = "insert into book (id, title) values (?, ?)";
        jdbcTemplate.update(query, book.getId(), book.getTitle());
    }

    //updates book in db
    public void updateBook(long id, Book newBook) {
        String query = "update book set  id = ?, title = ? where id = ?";
        jdbcTemplate.update(query, newBook.getId(), newBook.getTitle(), id);
    }

    //deletes book from db
    public void deleteBook(long id) {
        String query = "delete from book where id = ?";
        jdbcTemplate.update(query, id);
    }
}

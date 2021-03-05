package no.niklas.oeving3.dao;

import no.niklas.oeving3.model.BACRowMapper;
import no.niklas.oeving3.model.BookAuthorConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookAuthorConnectionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<BookAuthorConnection> getConnections() {
        String query = "select * from bookAuthor";
        return jdbcTemplate.query(query, new BACRowMapper());
    }

    public List<BookAuthorConnection> getConnectionsByBookId(long bookId) {
        String query = "select * from bookAuthor where bookid = ?";
        return jdbcTemplate.query(query, new BACRowMapper(), bookId);
    }

    public List<BookAuthorConnection> getConnectionsByAuthorId(long authorId) {
        String query = "select * from bookAuthor where authorid = ?";
        return jdbcTemplate.query(query, new BACRowMapper(), authorId);
    }

    public void addBookAuthorConnection(BookAuthorConnection bookAuthorConnection) {
        String query = "insert into bookAuthor (bookid, authorid) values (?, ?)";
        jdbcTemplate.update(query, bookAuthorConnection.getBookId(), bookAuthorConnection.getAuthorId());
    }

    public void deleteBookAuthorConnection(BookAuthorConnection bookAuthorConnection) {
        String query = "delete from bookAuthor where bookid = ? and authorid = ?";
        jdbcTemplate.update(query, bookAuthorConnection.getBookId(), bookAuthorConnection.getAuthorId());
    }

    public void addBookAuthorConnections(List<BookAuthorConnection> bookAuthorConnections) {
        for(BookAuthorConnection bac : bookAuthorConnections) addBookAuthorConnection(bac);
    }

    public void deleteBookAuthorConnections(List<BookAuthorConnection> bookAuthorConnections) {
        for(BookAuthorConnection bac : bookAuthorConnections) deleteBookAuthorConnection(bac);
    }
}

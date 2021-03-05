package no.niklas.oeving3.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BACRowMapper implements RowMapper<BookAuthorConnection> {
    @Override
    public BookAuthorConnection mapRow(ResultSet resultSet, int i) throws SQLException {
        BookAuthorConnection bookAuthorConnection = new BookAuthorConnection();

        bookAuthorConnection.setAuthorId(resultSet.getLong("authorid"));
        bookAuthorConnection.setBookId(resultSet.getLong("bookid"));

        return bookAuthorConnection;
    }
}

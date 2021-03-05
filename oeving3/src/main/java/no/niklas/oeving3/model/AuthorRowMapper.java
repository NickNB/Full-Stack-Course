package no.niklas.oeving3.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();

        author.setId(resultSet.getLong("id"));
        author.setName(resultSet.getString("name"));

        Address address = new Address();
        address.setCity(resultSet.getString("city"));
        address.setStreet(resultSet.getString("street"));
        address.setZipCode(resultSet.getInt("zipcode"));

        author.setAddress(address);
        return author;
    }
}

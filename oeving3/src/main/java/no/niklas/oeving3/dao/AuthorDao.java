package no.niklas.oeving3.dao;

import no.niklas.oeving3.DummyDB;
import no.niklas.oeving3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //Gets all authors from db
    public List<Author> getAuthors() {
        String query = "select * from author";
        return jdbcTemplate.query(query, new AuthorRowMapper());
    }

    //get author by id from db
    public Author getAuthorById(long id) {
        String query = "select * from author where id = ?";
        return jdbcTemplate.queryForObject(query, new AuthorRowMapper(), id);
    }

    //creates adds new author to db
    public void addAuthor(Author author) {
        String query = "insert into author (id, name, city, street, zipcode) values (?, ?, ?, ?, ?)";
        Address address = author.getAddress();
        jdbcTemplate.update(query, author.getId(), author.getName(), address.getCity(), address.getStreet(), address.getZipCode());
    }

    //updates author in db
    public void updateAuthor(long id, Author author) {
        String query = "update author set id = ?, name = ?, city = ?, street = ?, zipcode = ? where id = ?";
        Address address = author.getAddress();
        jdbcTemplate.update(query, author.getId(), author.getName(), address.getCity(), address.getStreet(), address.getZipCode(), id);
    }

    //deletes author from db
    public void deleteAuthor(long id) {
        String query = "delete from author where id = ?";
        jdbcTemplate.update(query, id);
    }
}

package no.niklas.oeving2.dao;

import no.niklas.oeving2.DummyDB;
import no.niklas.oeving2.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    //Gets all books from db
    public List<Book> getBooks() {
        return DummyDB.books;
    }

    //get book by id from db
    public Book getBookById(long id) {
        for (Book book: DummyDB.books) {
            if(book.getId() == id) return book;
        }
        return null;
    }

    //creates adds new book to db
    public void addBook(Book book) {
        DummyDB.books.add(book);
    }

    //updates book in db
    public void updateBook(long id, Book newBook) {
        DummyDB.books.removeIf(book -> id == book.getId());
        if(newBook.getId() == 0) newBook.setId(id);
        DummyDB.books.add(newBook);
    }

    //deletes book from db
    public void deleteBook(long id) {
        DummyDB.books.removeIf(book -> id == book.getId());
    }
}

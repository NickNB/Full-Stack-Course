package no.niklas.oeving3.web;

import no.niklas.oeving3.dao.BookAuthorConnectionDao;
import no.niklas.oeving3.model.Book;
import no.niklas.oeving3.model.BookAuthorConnection;
import no.niklas.oeving3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookAuthorConnectionDao bookAuthorConnectionDao;

    @GetMapping()
    public List<Book> getBooks(@RequestParam(required = false) String search) {
        //List<BookAuthorConnection> bookAuthorConnections = bookAuthorConnectionDao.getConnectionsByAuthorId(456);
        //bookAuthorConnections.forEach(con -> System.out.println(con.getBookId() + ", " + con.getAuthorId()));
        //BookAuthorConnection bookAuthorConnection = new BookAuthorConnection();
        //bookAuthorConnection.setBookId(111);
        //bookAuthorConnection.setAuthorId(222);
        //bookAuthorConnectionDao.deleteBookAuthorConnection(bookAuthorConnection);

        if(search == null) return bookService.getBooks();
        else return bookService.search(search);

        //return null;
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBook(id);
    }

    @PostMapping()
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("{id}")
    public String updateBook(@PathVariable long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable long id) {
        return bookService.deleteBook(id);
    }
}

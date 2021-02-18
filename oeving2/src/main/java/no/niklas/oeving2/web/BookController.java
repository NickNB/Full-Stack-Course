package no.niklas.oeving2.web;

import no.niklas.oeving2.model.Book;
import no.niklas.oeving2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping()
    public List<Book> getBooks(@RequestParam(required = false) String search) {
        if(search == null) return bookService.getBooks();
        else return bookService.search(search);
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

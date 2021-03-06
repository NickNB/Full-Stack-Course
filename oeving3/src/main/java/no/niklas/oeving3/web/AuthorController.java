package no.niklas.oeving3.web;

import no.niklas.oeving3.model.Author;
import no.niklas.oeving3.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public List<Author> getAuthors(@RequestParam(required = false) String search) {
        if(search == null) return authorService.getAuthors();
        else return authorService.search(search);
    }

    @GetMapping("{id}")
    public Author getAuthor(@PathVariable long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping()
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("{id}")
    public String updateAuthor(@PathVariable long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("{id}")
    public String deleteAuthor(@PathVariable long id) {
        return authorService.deleteAuthor(id);
    }
}
package no.niklas.oeving3;

import no.niklas.oeving3.dao.BookAuthorConnectionDao;
import no.niklas.oeving3.dao.BookDao;
import no.niklas.oeving3.model.Book;
import no.niklas.oeving3.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookDao bookDao;
    @Mock
    private BookAuthorConnectionDao bookAuthorConnectionDao;

    @BeforeEach
    public void setUp() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");

        Mockito.lenient().when(bookDao.getBookById(1)).thenReturn(book);
        Mockito.lenient().when(bookDao.getBooks()).thenReturn(new ArrayList<>());
        Mockito.lenient().when(bookAuthorConnectionDao.getConnectionsByBookId(1)).thenReturn(new ArrayList<>());

        lenient().doNothing().when(bookDao).addBook(book);
        lenient().doNothing().when(bookDao).updateBook(1, book);
        lenient().doNothing().when(bookDao).deleteBook(1);
        lenient().doNothing().when(bookAuthorConnectionDao).addBookAuthorConnections(new ArrayList<>());
        lenient().doNothing().when(bookAuthorConnectionDao).deleteBookAuthorConnections(new ArrayList<>());
    }

    @Test
    void addBook() {
        Book book = new Book();
        book.setId(5000);
        book.setTitle("en test bok");
        Book newBook = bookService.createBook(book);

        assertThat(newBook.getTitle()).isEqualTo("en test bok");
        assertThat(newBook.getId()).isEqualTo(5000);
    }

    @Test
    void updateBook() {
        String ret = bookService.updateBook(1, new Book());
        assertThat(ret).isEqualTo("Updated Book");
    }

    @Test
    void deleteBook() {
        String ret = bookService.deleteBook(1);
        assertThat(ret).isEqualTo("Deleted Book");
    }

    @Test
    void getBook() {
        Book book = bookService.getBook(1);

        assertThat(book.getTitle()).isEqualTo("Test Book");
        assertThat(book.getId()).isEqualTo(1);
    }
}

package no.niklas.oeving3;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookDao bookDao;

    @BeforeEach
    public void setUp() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");

        Mockito.lenient().when(bookDao.getBookById(1)).thenReturn(book);
        lenient().doNothing().when(bookDao).addBook(book);
    }

    @Test
    void addBook() {
        try {
            bookDao.addBook(new Book());
        } catch (IllegalArgumentException iae) {
            System.out.println("We should never have reached this point.");
            System.out.println("Did we somehow call the real method in stead of the mocked one?");
            fail();
        }
    }

    @Test
    void getBook() {
        Book book = bookDao.getBookById(1);

        assertThat(book.getTitle()).isEqualTo("Test Book");
        assertThat(book.getId()).isEqualTo(1);
    }
}

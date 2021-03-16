package no.niklas.oeving3;

import no.niklas.oeving3.model.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK, classes = Oeving3Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void getAllBooksTest() throws Exception {
        mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    @Order(2)
    void addBookTest() throws Exception {
        mockMvc.perform(post("/books/")
                .content(
                "{ \"id\": 1000, \"title\":\"test bok\" }").
                contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.id", is(1000)))
                .andExpect(jsonPath("$.title", is("test bok")));
    }

    @Test
    @Order(3)
    void getBookTest() throws Exception {
        mockMvc.perform(get("/books/1000/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.id", is(1000)))
                .andExpect(jsonPath("$.title", is("test bok")));
    }

    @Test
    @Order(4)
    void editBookTest() throws Exception {
        mockMvc.perform(put("/books/1000")
                .content(
                "{ \"id\": 1000, \"title\":\"test bok update\" }").
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deleteBookTest() throws Exception {
        mockMvc.perform(delete("/books/1000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

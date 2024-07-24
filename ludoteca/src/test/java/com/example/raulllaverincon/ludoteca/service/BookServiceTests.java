package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.Book;
import com.example.raulllaverincon.ludoteca.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindAll() {
        List<Book> books = bookService.findAll();
        assertThat(books).isNotEmpty();
    }

    @Test
    public void testFindById() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setYear(2024);
        book = bookRepository.save(book);

        Book foundBook = bookService.findById(book.getId());
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveBook() {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("New Author");
        book.setYear(2024);

        Book savedBook = bookService.save(book);
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteBook() {
        Book book = new Book();
        book.setTitle("To Be Deleted");
        book.setAuthor("Author");
        book.setYear(2024);
        book = bookRepository.save(book);

        bookService.deleteById(book.getId());
        Book deletedBook = bookService.findById(book.getId());
        assertThat(deletedBook).isNull();
    }
}
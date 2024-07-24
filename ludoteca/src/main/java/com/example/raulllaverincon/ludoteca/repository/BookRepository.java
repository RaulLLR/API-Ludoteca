package com.example.raulllaverincon.ludoteca.repository;

import com.example.raulllaverincon.ludoteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
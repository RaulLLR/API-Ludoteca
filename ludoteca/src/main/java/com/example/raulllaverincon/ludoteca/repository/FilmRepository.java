package com.example.raulllaverincon.ludoteca.repository;

import com.example.raulllaverincon.ludoteca.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.Film;
import com.example.raulllaverincon.ludoteca.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FilmServiceTests {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void testFindAll() {
        List<Film> films = filmService.findAll();
        assertThat(films).isNotEmpty();
    }

    @Test
    public void testFindById() {
        Film film = new Film();
        film.setFilm("Test Film");
        film.setReleaseYear(2024);
        film.setDirector("Test Director");
        film = filmRepository.save(film);

        Film foundFilm = filmService.findById(film.getId());
        assertThat(foundFilm).isNotNull();
        assertThat(foundFilm.getFilm()).isEqualTo("Test Film");
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveFilm() {
        Film film = new Film();
        film.setFilm("New Film");
        film.setReleaseYear(2024);
        film.setDirector("New Director");

        Film savedFilm = filmService.save(film);
        assertThat(savedFilm).isNotNull();
        assertThat(savedFilm.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteFilm() {
        Film film = new Film();
        film.setFilm("To Be Deleted");
        film.setReleaseYear(2024);
        film.setDirector("Director");
        film = filmRepository.save(film);

        filmService.deleteById(film.getId());
        Film deletedFilm = filmService.findById(film.getId());
        assertThat(deletedFilm).isNull();
    }
}
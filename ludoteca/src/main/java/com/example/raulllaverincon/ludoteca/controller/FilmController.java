package com.example.raulllaverincon.ludoteca.controller;

import com.example.raulllaverincon.ludoteca.model.Film;
import com.example.raulllaverincon.ludoteca.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = filmService.findById(id);
        return film != null ? ResponseEntity.ok(film) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        return filmService.save(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmService.deleteById(id);
    }
}
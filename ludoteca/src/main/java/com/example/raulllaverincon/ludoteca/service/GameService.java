package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.Game;
import com.example.raulllaverincon.ludoteca.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}
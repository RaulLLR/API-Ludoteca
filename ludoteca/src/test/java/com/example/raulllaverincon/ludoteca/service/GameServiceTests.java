package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.Game;
import com.example.raulllaverincon.ludoteca.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GameServiceTests {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testFindAll() {
        List<Game> games = gameService.findAll();
        assertThat(games).isNotEmpty();
    }

    @Test
    public void testFindById() {
        Game game = new Game();
        game.setGameTitle("Test Game");
        game.setPlatform("Test Platform");
        game.setYear(2024);
        game = gameRepository.save(game);

        Game foundGame = gameService.findById(game.getId());
        assertThat(foundGame).isNotNull();
        assertThat(foundGame.getGameTitle()).isEqualTo("Test Game");
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveGame() {
        Game game = new Game();
        game.setGameTitle("New Game");
        game.setPlatform("New Platform");
        game.setYear(2024);

        Game savedGame = gameService.save(game);
        assertThat(savedGame).isNotNull();
        assertThat(savedGame.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteGame() {
        Game game = new Game();
        game.setGameTitle("To Be Deleted");
        game.setPlatform("Platform");
        game.setYear(2024);
        game = gameRepository.save(game);

        gameService.deleteById(game.getId());
        Game deletedGame = gameService.findById(game.getId());
        assertThat(deletedGame).isNull();
    }
}
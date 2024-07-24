package com.example.raulllaverincon.ludoteca.repository;

import com.example.raulllaverincon.ludoteca.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
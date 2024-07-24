package com.example.raulllaverincon.ludoteca.repository;

import com.example.raulllaverincon.ludoteca.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
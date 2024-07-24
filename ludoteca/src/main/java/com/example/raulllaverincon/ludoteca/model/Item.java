package com.example.raulllaverincon.ludoteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String type; // e.g., book, game, film
    private LocalDate borrowedDate;

    public boolean isOverdue() {
        // Example logic to determine if the item is overdue
        return LocalDate.now().isAfter(borrowedDate.plusDays(14)); // Assuming 2 weeks borrowing period
    }

    public static boolean isOverdue(Object o) {
        return false;
    }
}
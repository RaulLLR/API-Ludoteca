package com.example.raulllaverincon.ludoteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "users")
public class User {

    // Getters and setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String username;

    @Setter
    @Getter
    @Column(nullable = false)
    private String password;

    @Setter
    @Getter
    @Column(nullable = false)
    private String role;  // Example roles might include 'ADMIN' and 'CUSTOMER'

    @Column(nullable = false)
    private boolean penalized;

    // Constructors
    public User() {
    }

    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Collection<Object> getItemsBorrowed() {
        return java.util.List.of();
    }

    public void setPenalized(boolean b) {

    }

    public boolean isPenalized() {
        return penalized;
    }


}

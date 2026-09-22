package com.cinema.user;

import jakarta.persistence.*;
import java.time.OffsetDateTime;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    protected User() {
    }

    public User(String username, String email, String passwordHash,
                String role) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setUsername(String username) { this.username = username;
    }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.
            passwordHash = passwordHash; }
    public void setRole(String role) { this.role = role; }
}
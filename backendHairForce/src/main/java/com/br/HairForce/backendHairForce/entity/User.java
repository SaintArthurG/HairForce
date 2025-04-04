package com.br.HairForce.backendHairForce.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    private Instant creationTimeStamp;

    @UpdateTimestamp
    private Instant updatedTimeStamp;

    @Version
    private Long version;

    public User() {}

    public User(Long userId, String username, String email, String password, Instant creationTimeStamp, Instant updatedTimeStamp, Long version) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimeStamp = creationTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
        this.version = version;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Instant creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public Instant getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(Instant updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

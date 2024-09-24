package com.gn.testtaskriga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @Column(unique = true, nullable = false)
    private String login;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @Column(nullable = false)
    private String authorities;
}

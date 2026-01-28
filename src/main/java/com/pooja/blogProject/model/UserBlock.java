package com.pooja.blogProject.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User blocker;

    @ManyToOne
    private User blocked;

    private LocalDateTime blockedAt;
}

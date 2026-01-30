package com.pooja.blogProject.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="UComment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Blog blog;

    private String text;

    private LocalDateTime createdAt;
}


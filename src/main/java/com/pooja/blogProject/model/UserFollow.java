package com.pooja.blogProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UUserFollow")
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User following;
}

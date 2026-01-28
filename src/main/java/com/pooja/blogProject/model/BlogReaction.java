package com.pooja.blogProject.model;

import com.pooja.blogProject.EnumModel.ReactionType;
import jakarta.persistence.*;

@Entity
public class BlogReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Blog blog;

    @Enumerated(EnumType.STRING)
    private ReactionType reaction; // LIKE / DISLIKE
}



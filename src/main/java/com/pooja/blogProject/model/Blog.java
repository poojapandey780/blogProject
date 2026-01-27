package com.pooja.blogProject.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="blogsB",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "title"})
        })
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;
// blog created

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

}

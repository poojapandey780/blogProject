package com.pooja.blogProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="blogsB",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "title"})
        })
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;
// blog created

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

        @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
        private List<Comment> comments;


        @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
        private List<BlogReaction> reactions;

}

package com.pooja.blogProject.model;

import com.pooja.blogProject.EnumModel.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usersB")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(name="pen_name", unique = true, nullable = false)
    private String penName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String bio;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Blog> blogs = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (accountStatus == null) {
            accountStatus = AccountStatus.ACTIVE;
        }
    }


//    one poet can write many poem - so OneToMany relationship
// what happen to the child if parent happen - cascade true

}

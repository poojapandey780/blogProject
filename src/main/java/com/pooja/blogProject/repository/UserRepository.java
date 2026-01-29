package com.pooja.blogProject.repository;

import com.pooja.blogProject.model.PasswordResetToken;
import com.pooja.blogProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByPenName(String penName);
    boolean existsByEmail(String email);
}

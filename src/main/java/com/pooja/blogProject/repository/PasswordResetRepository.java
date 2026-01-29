package com.pooja.blogProject.repository;

import com.pooja.blogProject.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken , Long> {
 public boolean existsByOtp(String otp);
 public Optional<PasswordResetToken> findByOtpAndUser_EmailAndUsedFalseAndExpiresAtAfter(String otp,String email, LocalDateTime now);
}

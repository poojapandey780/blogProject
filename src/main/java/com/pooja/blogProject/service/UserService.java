package com.pooja.blogProject.service;

import com.pooja.blogProject.Util.OtpGenerator;
import com.pooja.blogProject.dto.PersonalInfoDto;
import com.pooja.blogProject.model.PasswordResetToken;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.repository.PasswordResetRepository;
import com.pooja.blogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Autowired
    public PasswordResetRepository passRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    public MailService mailService;

    public void registerUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setProfileImage("/images/avatar.jpg");
        userRepository.save(user);
    }




    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }



    public boolean existsByPenName(String penname)
    {
        return userRepository.existsByPenName(penname);
    }



    public void updatePersonalInfo(User user, PersonalInfoDto dto)
    {
        user.setPenName(dto.getPenName());
        user.setBio(dto.getBio());
        userRepository.save(user);
    }



    public void sendOtp(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // Generate OTP
        String otp = OtpGenerator.generateOtp();

        // Create token
        PasswordResetToken token = new PasswordResetToken();
        token.setOtp(otp);
        token.setUser(user);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        token.setUsed(false);

        passRepo.save(token);

        // Send mail
        mailService.sendOtpMail(user.getEmail(), otp);
    }

    public boolean emailExists(String email)
    {
        return userRepository.existsByEmail(email);
    }



//    check otp matched or not
    public boolean verifyOtp(String email, String otp)
    {
        Optional<PasswordResetToken> token = passRepo.findByOtpAndUser_EmailAndUsedFalseAndExpiresAtAfter(otp,email,LocalDateTime.now());

        if (token.isEmpty()) {
            return false;
        }

        // mark OTP as used
        PasswordResetToken resetToken = token.get();
        resetToken.setUsed(true);
        passRepo.save(resetToken);

        return true;
    }

    public boolean resetPassword(String email, String password)
    {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        return true;
    }

}

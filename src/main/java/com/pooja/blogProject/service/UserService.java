package com.pooja.blogProject.service;

import com.pooja.blogProject.model.User;
import com.pooja.blogProject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

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

}

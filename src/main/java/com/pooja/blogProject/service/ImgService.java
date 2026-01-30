package com.pooja.blogProject.service;

import com.pooja.blogProject.model.User;
import com.pooja.blogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImgService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Autowired
    private UserRepository userRepo;

    public void updateProfileImage(String email, MultipartFile image) {

        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // create path to save image
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir, "profile-images");

        // upload image on path
        try {
            Files.createDirectories(uploadPath);
            Files.copy(
                    image.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }

//        save public url to database
        user.setProfileImage("/img/profile-images/" + fileName);
        userRepo.save(user);
    }



}

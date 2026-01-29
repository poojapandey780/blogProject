package com.pooja.blogProject.service;

import com.pooja.blogProject.dto.ImageDto;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ImgService {
    @Autowired
    public ImageDto imgdto;

    @Autowired
    public UserRepository userRepo;


//    public void updateProfileImage(String email, MultipartFile img)
//    {
//        User user = userRepo.findByEmail(email);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//
//        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
//        Path uploadPath = Paths.get("uploads/profile-images");
//
//        try {
//            Files.createDirectories(uploadPath);
//            Files.copy(image.getInputStream(),
//                    uploadPath.resolve(fileName),
//                    StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new RuntimeException("Image upload failed");
//        }
//
//        user.setProfileImage("/uploads/profile-images/" + fileName);
//        userRepository.save(user);
//    }

}

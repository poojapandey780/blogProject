package com.pooja.blogProject.dto;

import com.pooja.blogProject.model.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
        private Long id;
        private String userName;
        private String penName;
        private String email;
        private String password;
        private String bio;
        private String profileImage;
        private List<Blog> blogs = new ArrayList<>();
}

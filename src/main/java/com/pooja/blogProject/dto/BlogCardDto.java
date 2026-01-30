package com.pooja.blogProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogCardDto {
        private Long blogId;
        private String title;
        private String contentPreview;
        private LocalDateTime createdAt;
        private String penName;
        private long likeCount;
        private long dislikeCount;
        private long commentCount;

}

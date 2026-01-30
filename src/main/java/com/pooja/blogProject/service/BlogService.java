package com.pooja.blogProject.service;

import com.pooja.blogProject.dto.BlogCardDto;
import com.pooja.blogProject.dto.BlogDto;
import com.pooja.blogProject.model.Blog;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    public BlogRepository blogRepository;

    public boolean existsByTitleAndUser(String title, User user)
    {
        return blogRepository.existsByTitleAndUser(title,user);
    }

    public boolean createBlog(BlogDto dto , User user)
    {
        Blog blog = new Blog();

        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setUser(user);

        blogRepository.save(blog);
        return true;
    }


//    find all blogs of current user
    public Page<BlogCardDto> getBlogsOfCurrentUser(Long userId, int page) {

        Pageable pageable = PageRequest.of(page, 4, Sort.by("createdAt").descending());

        Page<BlogCardDto> blogPage =
                blogRepository.findBlogsOfCurrentUser(userId, pageable);

        // create content preview
        blogPage.forEach(blog -> {
            String content = blog.getContentPreview();
            if (content != null && content.length() > 120) {
                blog.setContentPreview(content.substring(0, 120));
            }
        });

        return blogPage;
    }

}

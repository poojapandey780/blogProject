package com.pooja.blogProject.repository;

import com.pooja.blogProject.dto.BlogCardDto;
import com.pooja.blogProject.model.Blog;
import com.pooja.blogProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    boolean existsByTitleAndUser(String title, User user);



    //  Fetch blogs of current user with reactions & comments
    @Query("""
        SELECT new com.pooja.blogProject.dto.BlogCardDto(
            b.id,
            b.title,
            b.content,
            b.createdAt,
            u.penName,

            SUM(CASE WHEN r.reaction = com.pooja.blogProject.EnumModel.ReactionType.LIKE THEN 1 ELSE 0 END),
            SUM(CASE WHEN r.reaction = com.pooja.blogProject.EnumModel.ReactionType.DISLIKE THEN 1 ELSE 0 END),

            COUNT(DISTINCT c.id)
        )
        FROM Blog b
        JOIN b.user u
        LEFT JOIN b.reactions r
        LEFT JOIN b.comments c
        WHERE u.id = :userId
        GROUP BY b.id, u.penName
        ORDER BY b.createdAt DESC
    """)
    Page<BlogCardDto> findBlogsOfCurrentUser(
            @Param("userId") Long userId,
            Pageable pageable
    );
}

package com.example.springrest.myblog.repository;

import com.example.springrest.myblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
}

package com.example.springrest.myblog.repository;

import com.example.springrest.myblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post,Long> {

}

package com.example.springrest.myblog.service;

import com.example.springrest.myblog.payload.PostDTO;

import java.util.List;

public interface IPostService {

    public PostDTO createPost(PostDTO postDTO);

    public List<PostDTO> getAllPosts();

    public PostDTO getPostByID(Long id);
}

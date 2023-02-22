package com.example.springrest.myblog.service;

import com.example.springrest.myblog.payload.PostDTO;

import java.util.List;

public interface IPostService {

    public PostDTO createPost(PostDTO postDTO);

    public List<PostDTO> getAllPosts(int pageNo, int pageSize);

    public PostDTO getPostByID(Long id);
    public PostDTO updatePost(PostDTO postDTO, Long id);

    public void deletePost(Long id);
}

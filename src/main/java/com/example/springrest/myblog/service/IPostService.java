package com.example.springrest.myblog.service;

import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.payload.PostResponse;

import java.util.List;

public interface IPostService {

    public PostDTO createPost(PostDTO postDTO);

    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);

    public PostDTO getPostByID(Long id);
    public PostDTO updatePost(PostDTO postDTO, Long id);

    public void deletePost(Long id);
}

package com.example.springrest.myblog.service;

import com.example.springrest.myblog.payload.PostDTO;

public interface IPostService {

    public PostDTO createPost(PostDTO postDTO);
}

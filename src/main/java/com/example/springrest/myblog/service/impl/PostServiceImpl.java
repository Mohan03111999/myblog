package com.example.springrest.myblog.service.impl;

import com.example.springrest.myblog.entity.Post;
import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.repository.IPostRepository;
import com.example.springrest.myblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository iPostRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        //Convert DTO to Entity
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post newPost = iPostRepository.save(post);

        //Convert Entity to DTO
        PostDTO postResponse = new PostDTO();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}

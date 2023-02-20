package com.example.springrest.myblog.controller;

import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<PostDTO>(iPostService.createPost(postDTO), HttpStatus.CREATED);
    }
}

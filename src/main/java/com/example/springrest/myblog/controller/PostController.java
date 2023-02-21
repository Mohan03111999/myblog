package com.example.springrest.myblog.controller;

import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<PostDTO>(iPostService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return new ResponseEntity<List<PostDTO>>(iPostService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable Long id){
        return ResponseEntity.ok(iPostService.getPostByID(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Long id){
        return ResponseEntity.ok(iPostService.updatePost(postDTO,id));
    }
}

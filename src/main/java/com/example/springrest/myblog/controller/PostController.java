package com.example.springrest.myblog.controller;

import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.payload.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
            ){
        return new ResponseEntity<PostResponse>(iPostService.getAllPosts(pageNo,pageSize,sortBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable Long id){
        return ResponseEntity.ok(iPostService.getPostByID(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Long id){
        return ResponseEntity.ok(iPostService.updatePost(postDTO,id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        iPostService.deletePost(id);
        return ResponseEntity.ok("Post with id "+id+" successfully deleted");
    }
}

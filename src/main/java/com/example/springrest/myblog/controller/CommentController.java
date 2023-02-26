package com.example.springrest.myblog.controller;

import com.example.springrest.myblog.payload.CommentDTO;
import com.example.springrest.myblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long postId, @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(iCommentService.createComment(postId,commentDTO), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable Long postId){
        return new ResponseEntity<>(iCommentService.getAllComments(postId), HttpStatus.OK);
    }
}

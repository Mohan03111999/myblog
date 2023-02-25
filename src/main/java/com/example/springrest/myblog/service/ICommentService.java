package com.example.springrest.myblog.service;

import com.example.springrest.myblog.payload.CommentDTO;

public interface ICommentService {
    CommentDTO createComment(Long postID, CommentDTO comment);
}

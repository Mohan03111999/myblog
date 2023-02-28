package com.example.springrest.myblog.service;

import com.example.springrest.myblog.payload.CommentDTO;

import java.util.List;

public interface ICommentService {
    CommentDTO createComment(Long postID, CommentDTO comment);
    List<CommentDTO> getAllComments(Long postId);

    CommentDTO getCommentById(Long postId, Long id);

    CommentDTO updateComment(Long postId, Long commentId,  CommentDTO commentDTO);
}

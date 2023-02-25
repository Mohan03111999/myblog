package com.example.springrest.myblog.service.impl;

import com.example.springrest.myblog.entity.Comment;
import com.example.springrest.myblog.entity.Post;
import com.example.springrest.myblog.exception.ResourceNotFoundException;
import com.example.springrest.myblog.payload.CommentDTO;
import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.repository.ICommentRepository;
import com.example.springrest.myblog.repository.IPostRepository;
import com.example.springrest.myblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository iCommentRepository;
    @Autowired
    private IPostRepository iPostRepository;
    @Override
    public CommentDTO createComment(Long postID, CommentDTO comment) {
        //Convert DTO to Entity to save
        Comment commentResponse = mapToEntity(comment);
        //retrieve post by id
        Post post = iPostRepository.findById(postID).orElseThrow(()->new ResourceNotFoundException("Post","id",postID));
        commentResponse.setPost(post);
        Comment newComment = iCommentRepository.save(commentResponse);
        return mapToDTO(newComment);
    }

    private CommentDTO mapToDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setName(comment.getName());
        commentDTO.setMessageBody(comment.getMessageBody());
        return commentDTO;
    }

    private Comment mapToEntity(CommentDTO commentDTO){
        Comment commentResponse = new Comment();
        commentResponse.setId(commentDTO.getId());
        commentResponse.setName(commentDTO.getName());
        commentResponse.setEmail(commentDTO.getEmail());
        commentResponse.setMessageBody(commentDTO.getMessageBody());
        return commentResponse;
    }
}

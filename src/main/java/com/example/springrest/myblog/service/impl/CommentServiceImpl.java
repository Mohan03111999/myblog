package com.example.springrest.myblog.service.impl;

import com.example.springrest.myblog.entity.Comment;
import com.example.springrest.myblog.entity.Post;
import com.example.springrest.myblog.exception.BlogAPIException;
import com.example.springrest.myblog.exception.ResourceNotFoundException;
import com.example.springrest.myblog.payload.CommentDTO;
import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.repository.ICommentRepository;
import com.example.springrest.myblog.repository.IPostRepository;
import com.example.springrest.myblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDTO> getAllComments(Long postId) {
        List<Comment> comment = iCommentRepository.findByPostId(postId);
        return comment.stream().map(comment1 -> mapToDTO(comment1)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long id) {
        //retrieve by post id
        Post post = iPostRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        //retrieve by comment id
        Comment comment = iCommentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","id",id));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        //retrieve by post id
        Post post = iPostRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        //retrieve by comment id
        Comment comment = iCommentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setMessageBody(commentDTO.getMessageBody());

        Comment updatedComment = iCommentRepository.save(comment);
        return mapToDTO(updatedComment);
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

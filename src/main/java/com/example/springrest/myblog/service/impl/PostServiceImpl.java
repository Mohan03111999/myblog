package com.example.springrest.myblog.service.impl;

import com.example.springrest.myblog.entity.Post;
import com.example.springrest.myblog.exception.ResourceNotFoundException;
import com.example.springrest.myblog.payload.PostDTO;
import com.example.springrest.myblog.repository.IPostRepository;
import com.example.springrest.myblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository iPostRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        //Convert DTO to Entity
        Post post = mapToEntity(postDTO);

        Post newPost = iPostRepository.save(post);

        //Convert Entity to DTO
        PostDTO postResponse = new PostDTO();
        return mapToDTO(newPost);
    }

    @Override
    public List<PostDTO> getAllPosts(int pageNo, int pageSize) {
        PageRequest pageable= PageRequest.of(pageNo,pageSize);
        Page<Post> postList = iPostRepository.findAll(pageable);
        /*List<PostDTO> postDTOS = new ArrayList<>();
        //convert entity to dto to return
        for(Post post: postList){
            postDTOS.add(mapToDTO(post));
        }*/
        List<Post> posts = postList.getContent();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostByID(Long id) {
        Post post = iPostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post post = iPostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatedPost = iPostRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = iPostRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id", id));
        iPostRepository.delete(post);
    }

    private PostDTO mapToDTO(Post post){
        PostDTO postResponse = new PostDTO();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setContent(post.getContent());
        return postResponse;
    }

    private Post mapToEntity(PostDTO postDTO){
        Post postResponse = new Post();
        postResponse.setTitle(postDTO.getTitle());
        postResponse.setDescription(postDTO.getDescription());
        postResponse.setContent(postDTO.getContent());
        return postResponse;
    }
}

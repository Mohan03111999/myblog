package com.example.springrest.myblog.payload;

import com.example.springrest.myblog.entity.Post;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String name;
    private String email;
    private String messageBody;
}

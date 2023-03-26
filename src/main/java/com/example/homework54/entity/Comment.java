package com.example.homework54.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private String text;
    private LocalDateTime dateTime;
    private Integer postId;
}

package com.example.homework54.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Integer id;
    private String image;
    private String description;
    private LocalDateTime dateTime;
    private String authorEmail;
}

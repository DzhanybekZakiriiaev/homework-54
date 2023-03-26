package com.example.homework54.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Followed {
    private Integer id;
    private Integer user;
    private Integer follower;
    private LocalDateTime dateTime;
}

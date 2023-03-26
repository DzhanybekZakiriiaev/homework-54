package com.example.homework54.dto;
import com.example.homework54.entity.Comment;
import com.example.homework54.entity.Post;
import com.example.homework54.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {
    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .email(user.getEmail())
                .followed(new ArrayList<>())
                .followers(new ArrayList<>())
                .password(user.getPassword())
                .posts(0)
                .build();
    }

    @Builder.Default
    private Integer id = null;
    private String name;
    private String email;
    private String password;
    private int posts;
    private List<User> followed;
    private List<User> followers;
}

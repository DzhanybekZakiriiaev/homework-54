package com.example.homework54.dao;

import com.example.homework54.dto.PostDTO;
import com.example.homework54.entity.Liked;
import com.example.homework54.entity.Post;
import com.example.homework54.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers(){
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public List<Liked> like(Integer postId) {
        String sql = "INSERT INTO liked (userEmail, postId, dateTime) " +
                "VALUES ('jane.doe@gmail.com'," + postId + ", '2022-03-16 12:05:00')";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Liked.class));
    }
    public List<Post> getAllPosts(){
        String sql = "select * from posts";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }
    public List<Post> getPosts(String email){
        String sql = "select * from posts where authorEmail = " + email;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }
    public List<Liked> getAllLikes(){
        String sql = "select * from likes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Liked.class));
    }
    public List<Post> post(PostDTO postDTO){
            String sql = "INSERT INTO post (image, description, dateTime, authorEmail) " +
                    "VALUES ('" + postDTO.getImage() + "','" + postDTO.getDescription() +
                    "', '" + postDTO.getTime().toString() + "', 'john.smith@gmail.com')";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }
    public List<Post> delete(Integer postId) {
            String sql = "\"DELETE FROM comments WHERE postID = \" + postId" +
                    "\"DELETE FROM posts WHERE id = \" + postId";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class));
    }
}
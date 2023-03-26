package com.example.homework54.dao;

import com.example.homework54.entity.Liked;
import com.example.homework54.entity.Post;
import com.example.homework54.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers(){
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> userByName(String name){
        String sql = "select * from users where name = " + name;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public List<User> userById(Integer id){
        String sql = "select * from users where account = " + id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public List<User> userByEmail(String email){
        String sql = "select * from users where email = " + email;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}

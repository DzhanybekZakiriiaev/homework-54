package com.example.homework54.service;

import com.example.homework54.dao.UserDao;
import com.example.homework54.dto.UserDTO;
import com.example.homework54.entity.Liked;
import com.example.homework54.entity.Post;
import com.example.homework54.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public List<User> getByName(String name){
        return userDao.userByName(name);
    }
    public List<User> getByEmail(String email){
        return userDao.userByEmail(email);
    }
    public List<User> getById(Integer id){
        return userDao.userById(id);
    }
    public Boolean isRegistred(String email){
        List<User> users = userDao.getAllUsers();
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
}

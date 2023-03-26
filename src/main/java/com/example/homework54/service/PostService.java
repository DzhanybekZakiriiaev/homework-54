package com.example.homework54.service;

import com.example.homework54.dao.PostDao;
import com.example.homework54.dao.UserDao;
import com.example.homework54.dto.PostDTO;
import com.example.homework54.entity.Liked;
import com.example.homework54.entity.Post;
import com.example.homework54.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }
    public List<Post> getPosts(String email){
        return postDao.getPosts(email);
    }
    public List<Post> getAllPosts(){
        return postDao.getAllPosts();
    }
    public List<Post> getFyp(String email){
        List<Post> fyp = new ArrayList<>();
        User owner = null;
        List<User> users = postDao.getAllUsers();
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getEmail().equals(email)){
                owner =  users.get(i);
            }
        }
        for (int i = 0; i < owner.getFollowed().size(); i++){
            fyp.addAll(getPosts(owner.getFollowed().get(i).getEmail()));
        }
        return fyp;
    }
    public Boolean isLiked(String email, Integer id){
        List<Liked> likes =  postDao.getAllLikes();
        for (int i = 0; i < likes.size(); i++){
            if (likes.get(i).getUser().getEmail().equals(email) && likes.get(i).getPost().getId() == id){
                return true;
            }
        }
        return false;
    }
    public List<Liked> like(Integer postId){
        return postDao.like(postId);
    }
    public List<Post> post(PostDTO postDTO){
        return postDao.post(postDTO);
    }
    public List<Post> delete(Integer postId){
        return postDao.delete(postId);
    }
}

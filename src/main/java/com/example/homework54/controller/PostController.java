package com.example.homework54.controller;

import com.example.homework54.dto.PostDTO;
import com.example.homework54.entity.Post;
import com.example.homework54.service.DataService;
import com.example.homework54.service.PostService;
import com.example.homework54.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/fyp/{email}")
    public ResponseEntity<List<Post>> getFyp(@PathVariable String email) {
        return new ResponseEntity<>(postService.getFyp(email), HttpStatus.OK);
    }
    @GetMapping("/liked/{email}/{postId}")
    public ResponseEntity<Boolean> isLiked(@PathVariable String email, @PathVariable Integer postId) {
        return new ResponseEntity<>(postService.isLiked(email, postId), HttpStatus.OK);
    }
    @GetMapping("/posts/{email}")
    public ResponseEntity<List<Post>> getPosts(@PathVariable String email) {
        return new ResponseEntity<>(postService.getPosts(email), HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<List<Post>> post(@RequestBody PostDTO postData){
        return new ResponseEntity<>(postService.post(postData), HttpStatus.OK);
    }
    @DeleteMapping ("/post/{postId}")
    public ResponseEntity<List<Post>> delete(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.delete(postId), HttpStatus.OK);
    }
}

package com.example.homework54.controller;
import com.example.homework54.entity.Comment;
import com.example.homework54.entity.Liked;
import com.example.homework54.service.DataService;
import com.example.homework54.service.PostService;
import com.example.homework54.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final PostService service;
    private final DataService dataService;

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId){
        return new ResponseEntity<>(dataService.deleteComment(commentId), HttpStatus.OK);
    }
    @PostMapping("/comment/{postId}")
    public ResponseEntity<String> comment(@PathVariable Integer postId, @RequestBody Comment comment){
        return new ResponseEntity<>(dataService.comment(postId, comment), HttpStatus.OK);
    }
    @PostMapping ("/comment/{postId}")
    public ResponseEntity<List<Liked>> like(@PathVariable Integer postId){
        return new ResponseEntity<>(service.like(postId), HttpStatus.OK);
    }
}

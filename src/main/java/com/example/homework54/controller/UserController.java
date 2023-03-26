package com.example.homework54.controller;
import com.example.homework54.dto.PostDTO;
import com.example.homework54.dto.UserDTO;
import com.example.homework54.entity.Comment;
import com.example.homework54.entity.Post;
import com.example.homework54.entity.User;
import com.example.homework54.service.DataService;
import com.example.homework54.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final DataService dataService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> users(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/user/name/{name}")
    public ResponseEntity<List<User>> byName(@PathVariable String name) {
        return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }
    @GetMapping("/user/email/{email}")
    public ResponseEntity<List<User>> byEmail(@PathVariable String email) {
        return new ResponseEntity<>(service.getByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/user/account/{id}")
    public ResponseEntity<List<User>> byAccount(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
    @GetMapping("/user/present/{email}")
    public ResponseEntity<Boolean> isRegistred(@PathVariable String email) {
        return new ResponseEntity<>(service.isRegistred(email), HttpStatus.OK);
    }
    @PostMapping("/reg")
    public ResponseEntity<String> reg(@RequestBody UserDTO userData){
        return new ResponseEntity<>(dataService.register(userData), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(){
        return new ResponseEntity<>(dataService.shouldCreateTable(), HttpStatus.OK);
    }
    @PostMapping("/insert")
    public ResponseEntity<String> insert(){
        return new ResponseEntity<>(dataService.shouldInsertValues(), HttpStatus.OK);
    }
    @PostMapping ("/subscribe/{userId}")
    public ResponseEntity<String> subscribe(@PathVariable Integer userId){
        return new ResponseEntity<>(dataService.subscribe(userId), HttpStatus.OK);
    }
}

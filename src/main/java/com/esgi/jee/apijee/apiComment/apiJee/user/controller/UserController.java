package com.esgi.jee.apijee.apiComment.apiJee.user.controller;


import com.esgi.jee.apijee.apiComment.apiJee.user.Dto.UserDto;
import com.esgi.jee.apijee.apiComment.apiJee.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(this.userService.getUserById(id),HttpStatus.OK);}
}

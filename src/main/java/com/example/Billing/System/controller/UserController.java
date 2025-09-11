package com.example.Billing.System.controller;

import java.util.List;

import org.apache.tomcat.util.log.UserDataHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Billing.System.model.ResponseUserDTO;
import com.example.Billing.System.model.UserDTO;
import com.example.Billing.System.repository.entity.User;
import com.example.Billing.System.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }   

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) throws Exception {
        userService.createUser(user);
        return ResponseEntity.ok("User "+ user.getName() + " is successfully added ");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserbyId(@PathVariable Long id) {
        ResponseUserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);    
    }

    @GetMapping("/use/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> allUsers() {

        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}

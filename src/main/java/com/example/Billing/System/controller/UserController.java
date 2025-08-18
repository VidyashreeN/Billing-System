package com.example.Billing.System.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Billing.System.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




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
    public ResponseEntity<UserDTO> getUserbyId(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);    
    }
    

}

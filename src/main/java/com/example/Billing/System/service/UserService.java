package com.example.Billing.System.service;

import java.util.List;

import com.example.Billing.System.controller.UserDTO;

public interface UserService {

    void createUser(UserDTO user) throws Exception;
    UserDTO getUserById(Long id);
    void updateUser(Long id, UserDTO userDTO) throws Exception;
    void deleteUser(Long id) throws Exception;
    List<UserDTO> getAllUsers();

}

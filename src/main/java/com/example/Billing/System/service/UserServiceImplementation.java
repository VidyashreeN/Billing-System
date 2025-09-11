package com.example.Billing.System.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Billing.System.model.ResponseUserDTO;
import com.example.Billing.System.model.UserDTO;
import com.example.Billing.System.repository.UserRepository;
import com.example.Billing.System.repository.entity.User;

@Service
public class UserServiceImplementation implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    UserServiceImplementation(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createUser(UserDTO userDTO) throws Exception {
        try{
            User user = modelMapper.map(userDTO, User.class);
             if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new Exception("Email already in use: " + user.getEmail());
            }
            userRepository.save(user);
        }
        catch(Exception e) {
            throw new Exception("Error creating user: " + e.getMessage());
        }
        System.out.println("User created successfully: " + userDTO.getName()  );
       
    }

    @Override
    public ResponseUserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        ResponseUserDTO userDTO = modelMapper.map(user, ResponseUserDTO.class);
        System.out.println("Retrieved User: " + userDTO.getName());
        return userDTO;
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) throws Exception {
        // Implementation logic for updating a user 
    }

    @Override
    public void deleteUser(Long id) throws Exception {
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

    }
    

}

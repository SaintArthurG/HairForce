package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.controller.DTO.CreateUserDTO;
import com.br.HairForce.backendHairForce.entity.User;
import com.br.HairForce.backendHairForce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(CreateUserDTO createUserDto){
        // DTO -> ENTITY

        User user = new User ();

        user.setUsername(createUserDto.getUsername());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());

        userRepository.save(user);
        return user.getUsername();
    }

    public List<User> showUsers(){
        return userRepository.findAll();
    }

}

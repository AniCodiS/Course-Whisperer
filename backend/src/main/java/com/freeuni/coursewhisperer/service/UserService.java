package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.mapper.UserMapper;
import com.freeuni.coursewhisperer.data.api.dto.CreatedUserDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserDTO;
import com.freeuni.coursewhisperer.data.entity.User;
import com.freeuni.coursewhisperer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            userDTOs.add(mapper.modelToDto(user));
        }
        return userDTOs;
    }

    public UserDTO getUserByUsername(String username) {
        return mapper.modelToDto(userRepository.findByUsername(username));
    }

    public CreatedUserDTO createUser(UserDTO lecturer) {
        CreatedUserDTO createdUserDTO = new CreatedUserDTO();
        User createdUser = userRepository.save(mapper.dtoToModel(lecturer));
        createdUserDTO.setId(createdUser.getId());
        createdUserDTO.setEmail(createdUser.getEmail());
        createdUserDTO.setUsername(createdUser.getUsername());
        createdUserDTO.setPassword(createdUser.getPassword());
        return createdUserDTO;
    }

    public UserDTO updateUser(String username, UserDTO user) {
        if (userRepository.existsByUsername(username)) {
            User userEntity = mapper.dtoToModel(user);
            userEntity.setId(userRepository.findByUsername(username).getId());
            return mapper.modelToDto(userRepository.save(userEntity));
        }
        return null;
    }

    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
}

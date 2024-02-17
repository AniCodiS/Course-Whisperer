package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.mapper.UserMapper;
import com.freeuni.coursewhisperer.data.model.User;
import com.freeuni.coursewhisperer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::entityToModel).toList();
    }

    public User getUserByUsername(String username) {
        return mapper.entityToModel(userRepository.findByUsername(username));
    }

    public User getUserByUserId (Long userId) {
        return mapper.entityToModel(userRepository.findById(userId).get());
    }

    public User createUser(User lecturer) {
        return mapper.entityToModel(userRepository.save(mapper.modelToEntity(lecturer)));
    }

    public User updateUser(String username, User user) {
        if (userRepository.existsByUsername(username)) {
            user.setId(userRepository.findByUsername(username).getId());
            return mapper.entityToModel(userRepository.save(mapper.modelToEntity(user)));
        }
        return null;
    }

    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
}

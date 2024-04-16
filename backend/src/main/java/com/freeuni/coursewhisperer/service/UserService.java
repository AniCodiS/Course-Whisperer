package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.*;
import com.freeuni.coursewhisperer.data.mapper.UpdateUserMapper;
import com.freeuni.coursewhisperer.data.mapper.UpdatedUserMapper;
import com.freeuni.coursewhisperer.data.mapper.UserMapper;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.data.mapper.UserResponseMapper;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final UpdateUserMapper updateMapper;

    private final UpdatedUserMapper updatedUserMapper;

    private final UserResponseMapper userResponseMapper;

    public UserService(UserRepository userRepository, UserMapper mapper, UpdateUserMapper updateMapper, UpdatedUserMapper updatedUserMapper, UserResponseMapper userResponseMapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.updateMapper = updateMapper;
        this.updatedUserMapper = updatedUserMapper;
        this.userResponseMapper = userResponseMapper;
    }

    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponses = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw ExceptionFactory.NoUsersPresent();
        }
        for (UserEntity user : users) {
            userResponses.add(userResponseMapper.modelToDto(userResponseMapper.entityToModel(user)));
        }
        return userResponses;
    }

    public UserResponse getUserByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            return userResponseMapper.modelToDto(userResponseMapper.entityToModel(userRepository.findByUsername(username)));
        }
        throw ExceptionFactory.UserNotFound();
    }

    public UserResponse createUser(UserDTO userDTO) {
        if (!isValidPassword(userDTO.getPassword())) {
            throw ExceptionFactory.NotValidPassword();
        }
        String newUsername = userDTO.getUsername();
        String newEmail = userDTO.getEmail();
        if (userRepository.existsByUsername(newUsername)) {
            throw ExceptionFactory.UsernameAlreadyExists();
        }
        if (userRepository.existsByEmail(newEmail)) {
            throw ExceptionFactory.EmailAlreadyExists();
        }
        UserResponse userResponse = new UserResponse();
        UserEntity createdUser = userRepository.save(mapper.modelToEntity(mapper.dtoToModel(userDTO)));
        userResponse.setEmail(createdUser.getEmail());
        userResponse.setUsername(createdUser.getUsername());
        userResponse.setPassword(createdUser.getPassword());
        return userResponse;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                switch (ch) {
                    case '!':
                    case '@':
                    case '#':
                    case '$':
                    case '%':
                    case '^':
                    case '&':
                    case '*':
                        hasSpecialChar = true;
                        break;
                    default:
                        break;
                }
            }
        }
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    public UpdatedUserDTO updateUser(String username, UpdateUserDTO updateUserDTO) {
        if (userRepository.existsByUsername(username)) {
            UserEntity userEntity = updateMapper.modelToEntity(updateMapper.dtoToModel(updateUserDTO));
            userEntity.setId(userRepository.findByUsername(username).getId());
            userEntity.setPassword(userRepository.findByUsername(username).getPassword());
            return updatedUserMapper.modelToDto(updatedUserMapper.entityToModel(userRepository.save(userEntity)));
        }
        throw ExceptionFactory.UserNotFound();
    }

    public String changePassword(String username, ChangePasswordDTO changePasswordDTO) {
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        String confirmNewPassword = changePasswordDTO.getConfirmNewPassword();
        if (userRepository.existsByUsername(username)) {
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity.getPassword().equals(oldPassword)) {
                if (!newPassword.equals(confirmNewPassword)) {
                    throw ExceptionFactory.PasswordsDoNotMatch();
                }
                userEntity.setPassword(newPassword);
                userRepository.save(userEntity);
                return "Password changed successfully!";
            } else {
                throw ExceptionFactory.OldPasswordDoesNotMatch();
            }
        } else {
            throw ExceptionFactory.UserNotFound();
        }
    }

    @Transactional
    public void deleteUser(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        } else {
            throw ExceptionFactory.UserNotFound();
        }
    }
}

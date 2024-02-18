package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.ChangePasswordDTO;
import com.freeuni.coursewhisperer.data.api.dto.UpdateUserDTO;
import com.freeuni.coursewhisperer.data.mapper.UpdateUserMapper;
import com.freeuni.coursewhisperer.data.mapper.UserMapper;
import com.freeuni.coursewhisperer.data.api.dto.CreatedUserDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserDTO;
import com.freeuni.coursewhisperer.data.entity.UserEntity;
import com.freeuni.coursewhisperer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final UpdateUserMapper updateMapper;

    public UserService(UserRepository userRepository, UserMapper mapper, UpdateUserMapper updateMapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.updateMapper = updateMapper;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            userDTOs.add(mapper.modelToDto(mapper.entityToModel(user)));
        }
        return userDTOs;
    }

    public UserDTO getUserByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            return mapper.modelToDto(mapper.entityToModel(userRepository.findByUsername(username)));
        }
        // TODO: throw exception
        return null;
    }

    public CreatedUserDTO createUser(UserDTO userDTO) {
        String newUsername = userDTO.getUsername();
        String newEmail = userDTO.getEmail();
        if (!userRepository.existsByUsername(newUsername) && !userRepository.existsByEmail(newEmail)) {
            CreatedUserDTO createdUserDTO = new CreatedUserDTO();
            UserEntity createdUser = userRepository.save(mapper.modelToEntity(mapper.dtoToModel(userDTO)));
            createdUserDTO.setId(createdUser.getId());
            createdUserDTO.setEmail(createdUser.getEmail());
            createdUserDTO.setUsername(createdUser.getUsername());
            createdUserDTO.setPassword(createdUser.getPassword());
            return createdUserDTO;
        }
        // TODO: throw exception
        return null;
    }

    public UpdateUserDTO updateUser(String username, UpdateUserDTO updateUserDTO) {
        if (userRepository.existsByUsername(username)) {
            UserEntity userEntity = updateMapper.modelToEntity(updateMapper.dtoToModel(updateUserDTO));
            userEntity.setId(userRepository.findByUsername(username).getId());
            userEntity.setPassword(userRepository.findByUsername(username).getPassword());
            return updateMapper.modelToDto(updateMapper.entityToModel(userRepository.save(userEntity)));
        }
        // TODO: throw exception
        return null;
    }

    public boolean changePassword(String username, ChangePasswordDTO changePasswordDTO) {
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        String confirmNewPassword = changePasswordDTO.getConfirmNewPassword();
        if (userRepository.existsByUsername(username)) {
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity.getPassword().equals(oldPassword)) {
                if (!newPassword.equals(confirmNewPassword)) {
                    return false;
                }
                userEntity.setPassword(newPassword);
                userRepository.save(userEntity);
                return true;
            } else {
                return false;
                // TODO: throw exception
            }
        } else {
            return false;
            // TODO: throw exception
        }
    }

    @Transactional
    public void deleteUser(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        }
        // TODO: throw exception
    }
}

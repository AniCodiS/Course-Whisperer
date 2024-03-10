package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.api.dto.UserScoreResponse;
import com.freeuni.coursewhisperer.data.mapper.UserScoreMapper;
import com.freeuni.coursewhisperer.data.api.dto.CreatedUserScoreDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserScoreDTO;
import com.freeuni.coursewhisperer.data.entity.UserScoreEntity;
import com.freeuni.coursewhisperer.data.mapper.UserScoreResponseMapper;
import com.freeuni.coursewhisperer.exception.ExceptionFactory;
import com.freeuni.coursewhisperer.repository.UserRepository;
import com.freeuni.coursewhisperer.repository.UserScoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;

    private final UserRepository userRepository;

    private final UserScoreMapper mapper;

    private final UserScoreResponseMapper responseMapper;

    public UserScoreService(UserScoreRepository userScoreRepository, UserRepository userRepository, UserScoreMapper mapper, UserScoreResponseMapper responseMapper) {
        this.userScoreRepository = userScoreRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.responseMapper = responseMapper;
    }

    public List<UserScoreResponse> getAllUserScores() {
        List<UserScoreResponse> userScoreResponses = new ArrayList<>();
        if (userScoreRepository.findAll().isEmpty()) {
            throw ExceptionFactory.NoUserScoresPresent();
        }
        List<UserScoreEntity> userScores = userScoreRepository.findAll();
        for (UserScoreEntity userScore : userScores) {
            userScoreResponses.add(responseMapper.modelToDto(responseMapper.entityToModel(userScore)));
        }
        return userScoreResponses;
    }

    public UserScoreResponse getUserScoreByUsername(String username) {
        if (userScoreRepository.existsByUsername(username)) {
            return responseMapper.modelToDto(responseMapper.entityToModel(userScoreRepository.findByUsername(username)));
        }
        throw ExceptionFactory.UserScoreNotFound();
    }

    public CreatedUserScoreDTO createUserScore(UserScoreDTO userScoreDTO) {
        if (userScoreRepository.existsByUsername(userScoreDTO.getUsername())) {
            throw ExceptionFactory.UserScoreAlreadyExists();
        }
        if (!userRepository.existsByUsername(userScoreDTO.getUsername())) {
            throw ExceptionFactory.UserNotFound();
        }
        CreatedUserScoreDTO createdUserScoreDTO = new CreatedUserScoreDTO();
        UserScoreEntity createdUserScore = userScoreRepository.save(mapper.modelToEntity(mapper.dtoToModel(userScoreDTO)));
        createdUserScoreDTO.setId(createdUserScore.getId());
        createdUserScoreDTO.setUsername(createdUserScore.getUsername());
        createdUserScoreDTO.setScore(createdUserScore.getScore());
        return createdUserScoreDTO;
    }

    public UserScoreResponse updateUserScore(String username, UserScoreDTO userScore) {
        if (userScoreRepository.existsByUsername(username)) {
            UserScoreEntity userScoreEntity = mapper.modelToEntity(mapper.dtoToModel(userScore));
            userScoreEntity.setId(userScoreRepository.findByUsername(username).getId());
            return responseMapper.modelToDto(responseMapper.entityToModel(userScoreRepository.save(userScoreEntity)));
        }
        throw ExceptionFactory.UserScoreNotFound();
    }

    @Transactional
    public void deleteUserScore(String username) {
        if (userScoreRepository.existsByUsername(username)) {
            userScoreRepository.deleteByUsername(username);
        } else {
            throw ExceptionFactory.UserScoreNotFound();
        }
    }
}

package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.mapper.UserScoreMapper;
import com.freeuni.coursewhisperer.data.api.dto.CreatedUserScoreDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserScoreDTO;
import com.freeuni.coursewhisperer.data.entity.UserScore;
import com.freeuni.coursewhisperer.repository.UserScoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;

    private final UserScoreMapper mapper;

    public UserScoreService(UserScoreRepository userScoreRepository, UserScoreMapper mapper) {
        this.userScoreRepository = userScoreRepository;
        this.mapper = mapper;
    }

    public List<UserScoreDTO> getAllUserScores() {
        List<UserScoreDTO> userScoreDTOs = new ArrayList<>();
        List<UserScore> userScores = userScoreRepository.findAll();
        for (UserScore userScore : userScores) {
            userScoreDTOs.add(mapper.modelToDto(userScore));
        }
        return userScoreDTOs;
    }

    public UserScoreDTO getUserScoreByUsername(String username) {
        return mapper.modelToDto(userScoreRepository.findByUsername(username));
    }

    public CreatedUserScoreDTO createUserScore(UserScoreDTO userScoreDTO) {
        CreatedUserScoreDTO createdUserScoreDTO = new CreatedUserScoreDTO();
        UserScore createdUserScore = userScoreRepository.save(mapper.dtoToModel(userScoreDTO));
        createdUserScoreDTO.setId(createdUserScore.getId());
        createdUserScoreDTO.setUsername(createdUserScore.getUsername());
        createdUserScoreDTO.setScore(createdUserScore.getScore());
        return createdUserScoreDTO;
    }

    public UserScoreDTO updateUserScore(String username, UserScoreDTO userScore) {
        if (userScoreRepository.existsByUsername(username)) {
            UserScore userScoreEntity = mapper.dtoToModel(userScore);
            userScoreEntity.setId(userScoreRepository.findByUsername(username).getId());
            return mapper.modelToDto(userScoreRepository.save(userScoreEntity));
        }
        return null;
    }

    @Transactional
    public void deleteUserScore(String username) {
        userScoreRepository.deleteByUsername(username);
    }
}

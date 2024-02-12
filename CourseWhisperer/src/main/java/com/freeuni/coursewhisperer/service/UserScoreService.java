package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.data.mapper.UserScoreMapper;
import com.freeuni.coursewhisperer.data.api.dto.CreatedUserScoreDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserScoreDTO;
import com.freeuni.coursewhisperer.data.entity.UserScoreEntity;
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
        List<UserScoreEntity> userScores = userScoreRepository.findAll();
        for (UserScoreEntity userScore : userScores) {
            userScoreDTOs.add(mapper.modelToDto(mapper.entityToModel(userScore)));
        }
        return userScoreDTOs;
    }

    public UserScoreDTO getUserScoreByUsername(String username) {
        return mapper.modelToDto(mapper.entityToModel(userScoreRepository.findByUsername(username)));
    }

    public CreatedUserScoreDTO createUserScore(UserScoreDTO userScoreDTO) {
        CreatedUserScoreDTO createdUserScoreDTO = new CreatedUserScoreDTO();
        UserScoreEntity createdUserScore = userScoreRepository.save(mapper.modelToEntity(mapper.dtoToModel(userScoreDTO)));
        createdUserScoreDTO.setId(createdUserScore.getId());
        createdUserScoreDTO.setUsername(createdUserScore.getUsername());
        createdUserScoreDTO.setScore(createdUserScore.getScore());
        return createdUserScoreDTO;
    }

    public UserScoreDTO updateUserScore(String username, UserScoreDTO userScore) {
        if (userScoreRepository.existsByUsername(username)) {
            UserScoreEntity userScoreEntity = mapper.modelToEntity(mapper.dtoToModel(userScore));
            userScoreEntity.setId(userScoreRepository.findByUsername(username).getId());
            return mapper.modelToDto(mapper.entityToModel(userScoreRepository.save(userScoreEntity)));
        }
        return null;
    }

    @Transactional
    public void deleteUserScore(String username) {
        userScoreRepository.deleteByUsername(username);
    }
}

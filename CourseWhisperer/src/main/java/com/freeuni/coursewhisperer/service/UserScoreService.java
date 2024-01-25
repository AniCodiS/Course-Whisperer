package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.model.db.UserScore;
import com.freeuni.coursewhisperer.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserScoreService {

    private final UserScoreRepository userScoreRepository;

    @Autowired
    public UserScoreService(UserScoreRepository userScoreRepository) {
        this.userScoreRepository = userScoreRepository;
    }

    public List<UserScore> getAllUserScores() {
        return userScoreRepository.findAll();
    }

    public UserScore getUserScoreByUserId(Long userId) {
        return userScoreRepository.findById(userId).orElse(null);
    }

    public UserScore createUserScore(UserScore userScore) {
        return userScoreRepository.save(userScore);
    }

    public UserScore updateUserScore(Long userId, UserScore userScore) {
        if (userScoreRepository.existsById(userId)) {
            userScore.setUserId(userId);
            return userScoreRepository.save(userScore);
        }
        return null;
    }

    public void deleteUserScore(Long userId) {
        userScoreRepository.deleteById(userId);
    }
}

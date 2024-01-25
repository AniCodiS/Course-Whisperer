package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.model.db.UserScore;
import com.freeuni.coursewhisperer.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-scores")
public class UserScoreController {

    private final UserScoreService userScoreService;

    @Autowired
    public UserScoreController(UserScoreService userScoreService) {
        this.userScoreService = userScoreService;
    }

    @GetMapping
    public List<UserScore> getAllUserScores() {
        return userScoreService.getAllUserScores();
    }

    @GetMapping("/{userId}")
    public UserScore getUserScoreByUserId(@PathVariable Long userId) {
        return userScoreService.getUserScoreByUserId(userId);
    }

    @PostMapping
    public UserScore createUserScore(@RequestBody UserScore userScore) {
        return userScoreService.createUserScore(userScore);
    }

    @PutMapping("/{userId}")
    public UserScore updateUserScore(@PathVariable Long userId, @RequestBody UserScore userScore) {
        return userScoreService.updateUserScore(userId, userScore);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserScore(@PathVariable Long userId) {
        userScoreService.deleteUserScore(userId);
    }
}

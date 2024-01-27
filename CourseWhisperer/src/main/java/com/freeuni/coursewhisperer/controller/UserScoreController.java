package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CreatedUserScoreDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserScoreDTO;
import com.freeuni.coursewhisperer.service.UserScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-scores")
public class UserScoreController {

    private final UserScoreService userScoreService;

    public UserScoreController(UserScoreService userScoreService) {
        this.userScoreService = userScoreService;
    }

    @GetMapping
    public List<UserScoreDTO> getAllUserScores() {
        return userScoreService.getAllUserScores();
    }

    @GetMapping("/{username}")
    public UserScoreDTO getUserScoreByUsername(@PathVariable String username) {
        return userScoreService.getUserScoreByUsername(username);
    }

    @PostMapping
    public CreatedUserScoreDTO createUserScore(@RequestBody UserScoreDTO userScore) {
        return userScoreService.createUserScore(userScore);
    }

    @PutMapping("/{username}")
    public UserScoreDTO updateUserScore(@PathVariable String username, @RequestBody UserScoreDTO userScore) {
        return userScoreService.updateUserScore(username, userScore);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserScore(@PathVariable Long userId) {
        userScoreService.deleteUserScore(userId);
    }
}

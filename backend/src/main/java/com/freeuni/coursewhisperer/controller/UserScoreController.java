package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.api.dto.CreatedUserScoreDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserScoreDTO;
import com.freeuni.coursewhisperer.data.api.dto.UserScoreResponse;
import com.freeuni.coursewhisperer.exception.CourseWhispererException;
import com.freeuni.coursewhisperer.service.UserScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user-score")
public class UserScoreController {

    private final UserScoreService userScoreService;

    public UserScoreController(UserScoreService userScoreService) {
        this.userScoreService = userScoreService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserScoreResponse>> getAllUserScores() {
        try {
            return ResponseEntity.ok().body(userScoreService.getAllUserScores());
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(List.of(new UserScoreResponse(e.getErrorDescription())));
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserScoreResponse> getUserScoreByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(userScoreService.getUserScoreByUsername(username));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new UserScoreResponse(e.getErrorDescription()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CreatedUserScoreDTO> createUserScore(@RequestBody UserScoreDTO userScore) {
        try {
            return ResponseEntity.ok().body(userScoreService.createUserScore(userScore));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new CreatedUserScoreDTO(e.getErrorDescription()));
        }
    }

    @PutMapping("update/{username}")
    public ResponseEntity<UserScoreResponse> updateUserScore(@PathVariable String username, @RequestBody UserScoreDTO userScore) {
        try {
            return ResponseEntity.ok().body(userScoreService.updateUserScore(username, userScore));
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(new UserScoreResponse(e.getErrorDescription()));
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUserScore(@PathVariable String username) {
        try {
            userScoreService.deleteUserScore(username);
        } catch (CourseWhispererException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getErrorDescription());
        }
        return ResponseEntity.ok().body("User score deleted successfully");
    }
}

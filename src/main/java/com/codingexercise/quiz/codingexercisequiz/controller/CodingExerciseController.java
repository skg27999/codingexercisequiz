package com.codingexercise.quiz.codingexercisequiz.controller;

import com.codingexercise.quiz.codingexercisequiz.model.quiz.Quiz;
import com.codingexercise.quiz.codingexercisequiz.service.CodingExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CodingExerciseController {

    @Autowired
    private CodingExerciseService codingExerciseService;

    @GetMapping("/coding/exercise/quiz")
    public Map<String, List<Quiz>> getCodingExerciseQuiz() throws InterruptedException{
        return codingExerciseService.getCodingExerciseQuiz();
    }
}

package com.codingexercise.quiz.codingexercisequiz.service;

import com.codingexercise.quiz.codingexercisequiz.client.QuestionsClient;
import com.codingexercise.quiz.codingexercisequiz.model.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.codingexercise.quiz.codingexercisequiz.util.constants.Questions_Amount;
import static com.codingexercise.quiz.codingexercisequiz.util.constants.Questions_Category;

@Service
public class CodingExerciseService {

    @Autowired
    private QuestionsClient questionsClient;

    public Map<String, List<Quiz>> getCodingExerciseQuiz() throws InterruptedException {
        List<Quiz> quiz = new ArrayList<Quiz>();
        CompletableFuture<Quiz> quizOne = CompletableFuture.supplyAsync(() -> questionsClient.getQuiz(Questions_Amount, Questions_Category));
        CompletableFuture<Quiz> quizTwo = CompletableFuture.supplyAsync(() -> questionsClient.getQuiz(Questions_Amount, Questions_Category));
        CompletableFuture.allOf(quizOne.thenAccept(quiz::add), quizTwo.thenAccept(quiz::add)).join();
        Map<String, List<Quiz>> wholeQuiz = new HashMap<>();
        wholeQuiz.put("quiz", quiz);
        return wholeQuiz;
    }
}

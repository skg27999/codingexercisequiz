package com.codingexercise.quiz.codingexercisequiz.model.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizQuestion {
    private String type;
    private String difficulty;
    private String question;
    @JsonProperty("all_answers")
    private List<String> allAnswers;
    @JsonProperty("correct_answer")
    private String correctAnswer;
}

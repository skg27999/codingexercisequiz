package com.codingexercise.quiz.codingexercisequiz.model.quiz;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {
    private String category;
    private List<QuizQuestion> results;
}

package com.codingexercise.quiz.codingexercisequiz.model.questions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionsResponse {
    @JsonProperty("response_code")
    private int responseCode;
    private List<Question> results;
}

package com.codingexercise.quiz.codingexercisequiz.client;

import com.codingexercise.quiz.codingexercisequiz.model.questions.QuestionsResponse;
import com.codingexercise.quiz.codingexercisequiz.model.questions.Question;
import com.codingexercise.quiz.codingexercisequiz.model.quiz.Quiz;
import com.codingexercise.quiz.codingexercisequiz.model.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionsClient {

    private RestTemplate restTemplate;

    @Value("${endpoints.questionsapi}")
    private String apiEndpoint;

    QuestionsClient() {
        this.restTemplate = new RestTemplate();
    }

    public Quiz getQuiz(int amount, int category) throws ResponseStatusException {
        try {
            String url = apiEndpoint + "?amount=" + amount + "&category=" + category;
            QuestionsResponse response = restTemplate.getForObject(url, QuestionsResponse.class);
            return mapResponseToQuiz(response.getResults());
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting Quiz data.");
        }
    }

    private Quiz mapResponseToQuiz(List<Question> questions) {
        String category = questions.get(0).getCategory();
        List<QuizQuestion> quizQuestions = questions.stream().map(question -> {
            QuizQuestion newQuizQuestion = new QuizQuestion();
            newQuizQuestion.setAllAnswers(question.getIncorrectAnswers());
            newQuizQuestion.getAllAnswers().add(question.getCorrectAnswer());
            newQuizQuestion.setCorrectAnswer(question.getCorrectAnswer());
            newQuizQuestion.setType(question.getType());
            newQuizQuestion.setQuestion(question.getQuestion());
            return newQuizQuestion;
        }).collect(Collectors.toList());
        return Quiz.builder().category(category).results(quizQuestions).build();
    }
}

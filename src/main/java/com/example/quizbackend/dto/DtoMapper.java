package com.example.quizbackend.dto;

import com.example.quizbackend.entity.AnswerEntity;
import com.example.quizbackend.entity.QuestionEntity;
import com.example.quizbackend.entity.QuizEntity;

import java.util.List;

public class DtoMapper {

    public static QuizResponse toQuizResponse(QuizEntity quiz) {
        List<QuestionResponse> questions = quiz.getQuestions() == null
                ? List.of()
                : quiz.getQuestions().stream().map(DtoMapper::toQuestionResponse).toList();

        return new QuizResponse(quiz.getId(), quiz.getTitle(), questions);
    }

    public static QuestionResponse toQuestionResponse(QuestionEntity q) {
        List<AnswerResponse> answers = q.getAnswers() == null
                ? List.of()
                : q.getAnswers().stream().map(DtoMapper::toAnswerResponse).toList();

        return new QuestionResponse(q.getId(), q.getText(), answers);
    }

    public static AnswerResponse toAnswerResponse(AnswerEntity a) {
        return new AnswerResponse(a.getId(), a.getText(), a.isCorrect());
    }
}

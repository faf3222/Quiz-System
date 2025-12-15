package com.example.quizbackend.dto;

import java.util.List;

public class QuestionRequest {
    private String text;
    private List<AnswerRequest> answers;

    public QuestionRequest() {}

    public QuestionRequest(String text, List<AnswerRequest> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequest> answers) {
        this.answers = answers;
    }
}

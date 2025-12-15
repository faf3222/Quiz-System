package com.example.quizbackend.dto;

import java.util.List;

public class QuestionCreateRequest {
    private String text;
    private List<AnswerCreateRequest> answers;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<AnswerCreateRequest> getAnswers() { return answers; }
    public void setAnswers(List<AnswerCreateRequest> answers) { this.answers = answers; }
}

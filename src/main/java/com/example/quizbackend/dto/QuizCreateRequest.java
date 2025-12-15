package com.example.quizbackend.dto;

import java.util.List;

public class QuizCreateRequest {
    private String title;
    private List<QuestionCreateRequest> questions;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<QuestionCreateRequest> getQuestions() { return questions; }
    public void setQuestions(List<QuestionCreateRequest> questions) { this.questions = questions; }
}

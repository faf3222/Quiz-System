package com.example.quizbackend.dto;

public class QuizRequest {
    private String title;

    public QuizRequest() {}

    public QuizRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

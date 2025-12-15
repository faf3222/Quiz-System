package com.example.quizbackend.dto;

public class AnswerRequest {
    private String text;
    private boolean correct;

    public AnswerRequest() {}

    public AnswerRequest(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}

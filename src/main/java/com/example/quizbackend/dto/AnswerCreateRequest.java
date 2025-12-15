package com.example.quizbackend.dto;

public class AnswerCreateRequest {
    private String text;
    private boolean correct;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
}

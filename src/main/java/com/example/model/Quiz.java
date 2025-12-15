package com.example.model;

import java.util.List;

public class Quiz implements IQuiz {
    private String title;
    private List<Question> questions;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public List<IQuestion> getQuestions() {
        return List.copyOf(questions);
    }
}

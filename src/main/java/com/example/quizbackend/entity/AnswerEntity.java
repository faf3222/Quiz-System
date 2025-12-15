package com.example.quizbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    public AnswerEntity() {}

    public AnswerEntity(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public Long getId() { return id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public boolean isCorrect() { return correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public QuestionEntity getQuestion() { return question; }

    public void setQuestion(QuestionEntity question) { this.question = question; }
}

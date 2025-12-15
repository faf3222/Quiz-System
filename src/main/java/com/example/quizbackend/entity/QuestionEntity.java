package com.example.quizbackend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerEntity> answers = new ArrayList<>();

    public QuestionEntity() {}

    public QuestionEntity(String text) {
        this.text = text;
    }

    public Long getId() { return id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public QuizEntity getQuiz() { return quiz; }

    public void setQuiz(QuizEntity quiz) { this.quiz = quiz; }

    public List<AnswerEntity> getAnswers() { return answers; }

    public void setAnswers(List<AnswerEntity> answers) { this.answers = answers; }
}

package com.example.quizbackend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();

    public QuizEntity() {}

    public QuizEntity(String title) {
        this.title = title;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<QuestionEntity> getQuestions() { return questions; }

    public void setQuestions(List<QuestionEntity> questions) { this.questions = questions; }
}

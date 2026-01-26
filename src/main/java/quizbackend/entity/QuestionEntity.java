package quizbackend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AnswerEntity> answers = new ArrayList<>();

    public QuestionEntity() {}

    public QuestionEntity(String text, QuizEntity quiz) {
        this.text = text;
        this.quiz = quiz;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public QuizEntity getQuiz() { return quiz; }
    public List<AnswerEntity> getAnswers() { return answers; }

    public void setId(Long id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setQuiz(QuizEntity quiz) { this.quiz = quiz; }
    public void setAnswers(List<AnswerEntity> answers) { this.answers = answers; }
}
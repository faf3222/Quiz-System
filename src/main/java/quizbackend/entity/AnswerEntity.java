package quizbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    public AnswerEntity() {}

    public AnswerEntity(String text, boolean correct, QuestionEntity question) {
        this.text = text;
        this.correct = correct;
        this.question = question;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public boolean isCorrect() { return correct; }
    public QuestionEntity getQuestion() { return question; }

    public void setId(Long id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setCorrect(boolean correct) { this.correct = correct; }
    public void setQuestion(QuestionEntity question) { this.question = question; }
}
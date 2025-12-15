package com.example.quizbackend.service;

import com.example.quizbackend.dto.AnswerCreateRequest;
import com.example.quizbackend.dto.QuestionCreateRequest;
import com.example.quizbackend.dto.QuizCreateRequest;
import com.example.quizbackend.entity.AnswerEntity;
import com.example.quizbackend.entity.QuestionEntity;
import com.example.quizbackend.entity.QuizEntity;
import com.example.quizbackend.exception.NotFoundException;
import com.example.quizbackend.repository.AnswerRepository;
import com.example.quizbackend.repository.QuestionRepository;
import com.example.quizbackend.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.quizbackend.entity.QuizEntity;
import com.example.quizbackend.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public QuizEntity createQuiz(QuizCreateRequest request) {
        QuizEntity quiz = new QuizEntity();
        quiz.setTitle(request.getTitle());
        return quizRepository.save(quiz);
    }

    @Transactional(readOnly = true)
    public List<QuizEntity> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Transactional(readOnly = true)
    public QuizEntity getQuiz(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new NotFoundException("Quiz not found: " + quizId));
    }
    public List<QuizEntity> getAllQuizzesEntities() {
        return quizRepository.findAll();
    }

    @Transactional(readOnly = true)
    public QuizEntity getQuizEntityWithQuestions(Long id) {
        QuizEntity quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found: " + id));
        quiz.getQuestions().forEach(q -> q.getAnswers().size());
        return quiz;
    }

    public QuizEntity updateQuizTitle(Long quizId, String title) {
        QuizEntity quiz = getQuiz(quizId);
        quiz.setTitle(title);
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long quizId) {
        QuizEntity quiz = getQuiz(quizId);
        quizRepository.delete(quiz);
    }

    public QuestionEntity addQuestion(Long quizId, QuestionCreateRequest request) {
        QuizEntity quiz = getQuiz(quizId);

        QuestionEntity question = new QuestionEntity();
        question.setText(request.getText());
        question.setQuiz(quiz);

        QuestionEntity savedQuestion = questionRepository.save(question);

        if (request.getAnswers() != null) {
            for (AnswerCreateRequest a : request.getAnswers()) {
                AnswerEntity answer = new AnswerEntity();
                answer.setText(a.getText());
                answer.setCorrect(a.isCorrect());
                answer.setQuestion(savedQuestion);
                answerRepository.save(answer);
            }
        }

        return savedQuestion;
    }

    public AnswerEntity addAnswer(Long quizId, Long questionId, AnswerCreateRequest request) {
        QuizEntity quiz = getQuiz(quizId);

        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found: " + questionId));

        if (question.getQuiz() == null || !question.getQuiz().getId().equals(quiz.getId())) {
            throw new NotFoundException("Question " + questionId + " does not belong to Quiz " + quizId);
        }

        AnswerEntity answer = new AnswerEntity();
        answer.setText(request.getText());
        answer.setCorrect(request.isCorrect());
        answer.setQuestion(question);

        return answerRepository.save(answer);
    }
}

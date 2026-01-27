package quizbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quizbackend.dto.*;
import quizbackend.entity.AnswerEntity;
import quizbackend.entity.QuestionEntity;
import quizbackend.entity.QuizEntity;
import quizbackend.exception.NotFoundException;
import quizbackend.repository.AnswerRepository;
import quizbackend.repository.QuestionRepository;
import quizbackend.repository.QuizRepository;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuizService(QuizRepository quizRepository,
                       QuestionRepository questionRepository,
                       AnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    // =========================
    // REST API methods (Postman)
    // =========================
    public List<QuizResponse> getAll() {
        return quizRepository.findAll()
                .stream()
                .map(this::toQuizResponse)
                .toList();
    }

    public QuizResponse getById(Long id) {
        QuizEntity quiz = quizRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quiz not found with id=" + id));
        return toQuizResponse(quiz);
    }

    public QuizResponse create(QuizCreateRequest request) {
        QuizEntity quiz = new QuizEntity();
        quiz.setTitle(request.title());
        QuizEntity saved = quizRepository.save(quiz);
        return toQuizResponse(saved);
    }

    private QuizResponse toQuizResponse(QuizEntity quiz) {
        return new QuizResponse(quiz.getId(), quiz.getTitle());
    }

    // =========================
    // UI methods (Thymeleaf)
    // =========================
    public QuestionView getQuestionView(Long quizId, int index) {
        QuizEntity quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NotFoundException("Quiz not found with id=" + quizId));

        List<QuestionEntity> questions = questionRepository.findByQuizId(quizId)
                .stream()
                .sorted(Comparator.comparing(QuestionEntity::getId))
                .toList();

        if (questions.isEmpty()) {
            throw new NotFoundException("No questions found for quiz id=" + quizId);
        }

        if (index < 0 || index >= questions.size()) {
            throw new NotFoundException("Question index out of range: " + index);
        }

        QuestionEntity q = questions.get(index);

        List<AnswerOption> options = answerRepository.findByQuestionId(q.getId())
                .stream()
                .map(a -> new AnswerOption(a.getId(), a.getText()))
                .toList();

        return new QuestionView(
                quiz.getId(),
                quiz.getTitle(),
                index,
                questions.size(),
                q.getText(),
                options
        );
    }

    public boolean isCorrect(Long quizId, int index, Long answerId) {
        // Make sure the question exists (valid index)
        QuestionView view = getQuestionView(quizId, index);

        AnswerEntity ans = answerRepository.findById(answerId)
                .orElseThrow(() -> new NotFoundException("Answer not found with id=" + answerId));

        // Safety: ensure answer belongs to current question
        Long currentQuestionId = questionRepository.findByQuizId(quizId)
                .stream()
                .sorted(Comparator.comparing(QuestionEntity::getId))
                .toList()
                .get(index)
                .getId();

        if (!ans.getQuestion().getId().equals(currentQuestionId)) {
            throw new NotFoundException("Answer does not belong to this question");
        }

        return ans.isCorrect();
    }

    public boolean isFinished(Long quizId, int index) {
        return index >= getTotalQuestions(quizId);
    }

    public int getTotalQuestions(Long quizId) {
        return (int) questionRepository.countByQuizId(quizId);
    }
} 
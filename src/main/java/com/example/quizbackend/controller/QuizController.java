package com.example.quizbackend.controller;

import com.example.quizbackend.dto.*;
import com.example.quizbackend.entity.AnswerEntity;
import com.example.quizbackend.entity.QuestionEntity;
import com.example.quizbackend.entity.QuizEntity;
import com.example.quizbackend.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public QuizResponse create(@RequestBody QuizCreateRequest request) {
        QuizEntity quiz = quizService.createQuiz(request);
        return DtoMapper.toQuizResponse(quiz);
    }

    @GetMapping
    public List<QuizResponse> getAll() {
        return quizService.getAllQuizzes().stream()
                .map(DtoMapper::toQuizResponse)
                .toList();
    }

    @GetMapping("/{quizId}")
    public QuizResponse getOne(@PathVariable Long quizId) {
        return DtoMapper.toQuizResponse(quizService.getQuiz(quizId));
    }

    @PutMapping("/{quizId}")
    public QuizResponse updateTitle(@PathVariable Long quizId, @RequestBody QuizCreateRequest request) {
        QuizEntity updated = quizService.updateQuizTitle(quizId, request.getTitle());
        return DtoMapper.toQuizResponse(updated);
    }

    @DeleteMapping("/{quizId}")
    public void delete(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
    }

    @PostMapping("/{quizId}/questions")
    public QuestionResponse addQuestion(@PathVariable Long quizId, @RequestBody QuestionCreateRequest request) {
        QuestionEntity q = quizService.addQuestion(quizId, request);
        return DtoMapper.toQuestionResponse(q);
    }

    @PostMapping("/{quizId}/questions/{questionId}/answers")
    public AnswerResponse addAnswer(@PathVariable Long quizId, @PathVariable Long questionId, @RequestBody AnswerCreateRequest request) {
        AnswerEntity a = quizService.addAnswer(quizId, questionId, request);
        return DtoMapper.toAnswerResponse(a);
    }
}

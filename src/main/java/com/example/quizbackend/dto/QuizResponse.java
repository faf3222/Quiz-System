package com.example.quizbackend.dto;

import java.util.List;

public record QuizResponse(Long id, String title, List<QuestionResponse> questions) {}

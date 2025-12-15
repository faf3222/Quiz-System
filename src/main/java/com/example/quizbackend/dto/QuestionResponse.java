package com.example.quizbackend.dto;

import java.util.List;

public record QuestionResponse(Long id, String text, List<AnswerResponse> answers) {}

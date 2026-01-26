package quizbackend.dto;

public record AnswerCreateRequest(Long questionId, String text, boolean correct) {}
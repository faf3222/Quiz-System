package quizbackend.dto;

public record AnswerRequest(Long id, String text, boolean correct) {}
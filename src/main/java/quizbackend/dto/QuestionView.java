package quizbackend.dto;

import java.util.List;

public record QuestionView(
        Long quizId,
        String quizTitle,
        int index,
        int total,
        String questionText,
        List<AnswerOption> answers
) {}
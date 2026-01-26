package quizbackend.dto;

import org.springframework.stereotype.Component;
import quizbackend.entity.AnswerEntity;
import quizbackend.entity.QuestionEntity;
import quizbackend.entity.QuizEntity;

@Component
public class DtoMapper {

    public QuizResponse toQuizResponse(QuizEntity q) {
        return new QuizResponse(q.getId(), q.getTitle());
    }

    public QuestionResponse toQuestionResponse(QuestionEntity q) {
        return new QuestionResponse(q.getId(), q.getText());
    }

    public AnswerResponse toAnswerResponse(AnswerEntity a) {
        return new AnswerResponse(a.getId(), a.getText());
    }
}
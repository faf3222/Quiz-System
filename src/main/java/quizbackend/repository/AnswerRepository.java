package quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizbackend.entity.AnswerEntity;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    List<AnswerEntity> findByQuestionId(Long questionId);
}
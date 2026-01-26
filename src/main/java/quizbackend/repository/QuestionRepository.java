package quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizbackend.entity.QuestionEntity;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByQuizId(Long quizId);
    long countByQuizId(Long quizId);
}
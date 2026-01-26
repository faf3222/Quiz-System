package quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizbackend.entity.QuizSessionEntity;

public interface QuizSessionRepository extends JpaRepository<QuizSessionEntity, Long> {
}
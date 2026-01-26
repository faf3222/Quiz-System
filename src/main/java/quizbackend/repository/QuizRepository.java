package quizbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizbackend.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {}
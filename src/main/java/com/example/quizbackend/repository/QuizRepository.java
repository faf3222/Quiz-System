package com.example.quizbackend.repository;

import com.example.quizbackend.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {}

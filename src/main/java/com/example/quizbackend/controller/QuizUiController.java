package com.example.quizbackend.controller;

import com.example.quizbackend.entity.AnswerEntity;
import com.example.quizbackend.entity.QuestionEntity;
import com.example.quizbackend.entity.QuizEntity;
import com.example.quizbackend.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/ui")
public class QuizUiController {

    private final QuizService quizService;

    public QuizUiController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes")
    public String listQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.getAllQuizzesEntities());
        return "quizzes";
    }

    @GetMapping("/quizzes/{quizId}")
    public String takeQuiz(@PathVariable Long quizId, Model model) {
        QuizEntity quiz = quizService.getQuizEntityWithQuestions(quizId);
        model.addAttribute("quiz", quiz);
        return "quiz-take";
    }

    @PostMapping("/quizzes/{quizId}/submit")
    public String submitQuiz(@PathVariable Long quizId, @RequestParam Map<String, String> params, Model model) {
        QuizEntity quiz = quizService.getQuizEntityWithQuestions(quizId);

        int total = 0;
        int correct = 0;

        List<ResultRow> rows = new ArrayList<>();

        for (QuestionEntity q : quiz.getQuestions()) {
            total++;

            String key = "q_" + q.getId();
            String chosenAnswerIdStr = params.get(key);

            AnswerEntity chosen = null;
            AnswerEntity correctAnswer = null;

            for (AnswerEntity a : q.getAnswers()) {
                if (Boolean.TRUE.equals(a.isCorrect())) correctAnswer = a;
                if (chosenAnswerIdStr != null && chosenAnswerIdStr.equals(String.valueOf(a.getId()))) chosen = a;
            }

            boolean isCorrect = chosen != null && Boolean.TRUE.equals(chosen.isCorrect());
            if (isCorrect) correct++;

            rows.add(new ResultRow(
                    q.getText(),
                    chosen == null ? "(no answer)" : chosen.getText(),
                    correctAnswer == null ? "(no correct answer saved)" : correctAnswer.getText(),
                    isCorrect
            ));
        }

        model.addAttribute("quizTitle", quiz.getTitle());
        model.addAttribute("total", total);
        model.addAttribute("correct", correct);
        model.addAttribute("rows", rows);

        return "result";
    }

    public record ResultRow(String questionText, String chosenText, String correctText, boolean correct) {}
}

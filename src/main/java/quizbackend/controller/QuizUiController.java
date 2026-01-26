package quizbackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quizbackend.dto.QuestionView;
import quizbackend.dto.QuizResultView;
import quizbackend.service.QuizService;

@Controller
public class QuizUiController {

    private final QuizService quizService;

    public QuizUiController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes")
    public String quizzes(Model model) {
        model.addAttribute("quizzes", quizService.getAll());
        return "quizzes";
    }

    @GetMapping("/quizzes/{id}/take")
    public String takeQuiz(@PathVariable Long id,
                           @RequestParam(defaultValue = "0") int index,
                           Model model,
                           HttpSession session) {

        if (index == 0) {
            session.setAttribute(scoreKey(id), 0);
        }

        QuestionView qv = quizService.getQuestionView(id, index);
        model.addAttribute("qv", qv);
        return "quiz-take";
    }

    @PostMapping("/quizzes/{id}/answer")
    public String answer(@PathVariable Long id,
                         @RequestParam int index,
                         @RequestParam Long answerId,
                         HttpSession session) {

        boolean correct = quizService.isCorrect(id, index, answerId);

        Integer score = (Integer) session.getAttribute(scoreKey(id));
        if (score == null) score = 0;

        if (correct) score++;
        session.setAttribute(scoreKey(id), score);

        int nextIndex = index + 1;

        if (quizService.isFinished(id, nextIndex)) {
            return "redirect:/quizzes/" + id + "/result";
        }

        return "redirect:/quizzes/" + id + "/take?index=" + nextIndex;
    }

    @GetMapping("/quizzes/{id}/result")
    public String result(@PathVariable Long id, HttpSession session, Model model) {
        Integer score = (Integer) session.getAttribute(scoreKey(id));
        if (score == null) score = 0;

        int total = quizService.getTotalQuestions(id);

        model.addAttribute("result", new QuizResultView(id, score, total));
        return "result";
    }

    private String scoreKey(Long quizId) {
        return "QUIZ_SCORE_" + quizId;
    }
}
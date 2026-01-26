package quizbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import quizbackend.dto.QuizCreateRequest;
import quizbackend.dto.QuizResponse;
import quizbackend.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<QuizResponse> getAll() {
        return quizService.getAll();
    }

    @GetMapping("/{id}")
    public QuizResponse getById(@PathVariable Long id) {
        return quizService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponse create(@RequestBody QuizCreateRequest request) {
        return quizService.create(request);
    }
}
package quizbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quizbackend.service.ExternalQuizLoader;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/external")
public class ExternalLoadController {

    private final ExternalQuizLoader loader;

    public ExternalLoadController(ExternalQuizLoader loader) {
        this.loader = loader;
    }

    @PostMapping("/load")
    public ResponseEntity<String> load() throws Exception {
        // Replace these with real URLs from your teacher/external source
        String quizzesUrl = "https://example.com/quizzes.json";
        String questionsUrl = "https://example.com/questions.json";
        String answersUrl = "https://example.com/answers.json";

        CompletableFuture<String> quizzesF = loader.fetchQuizzesJson(quizzesUrl);
        CompletableFuture<String> questionsF = loader.fetchQuestionsJson(questionsUrl);
        CompletableFuture<String> answersF = loader.fetchAnswersJson(answersUrl);

        CompletableFuture.allOf(quizzesF, questionsF, answersF).join();

        // For Task 8 demo: returning result sizes is enough.
        String result = "Loaded external data concurrently:\n"
                + "quizzes json length=" + quizzesF.get().length() + "\n"
                + "questions json length=" + questionsF.get().length() + "\n"
                + "answers json length=" + answersF.get().length();

        return ResponseEntity.ok(result);
    }
}
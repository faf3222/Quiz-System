package quizbackend.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalQuizLoader {

    private final RestTemplate restTemplate;

    public ExternalQuizLoader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<String> loadQuizzes() {
        String url = "https://opentdb.com/api.php?amount=5&type=multiple";
        return fetchJson(url);
    }

    @Async
    public CompletableFuture<String> fetchQuizzesJson(String url) {
        return fetchJson(url);
    }

    @Async
    public CompletableFuture<String> fetchQuestionsJson(String url) {
        return fetchJson(url);
    }

    @Async
    public CompletableFuture<String> fetchAnswersJson(String url) {
        return fetchJson(url);
    }

    private CompletableFuture<String> fetchJson(String url) {
        String body = restTemplate.getForObject(url, String.class);
        return CompletableFuture.completedFuture(body);
    }
}
package quizbackend.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalQuizLoader {

    private final RestClient restClient;

    public ExternalQuizLoader(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @Async
    public CompletableFuture<String> fetchQuizzesJson(String url) {
        String body = restClient.get().uri(url).retrieve().body(String.class);
        return CompletableFuture.completedFuture(body);
    }

    @Async
    public CompletableFuture<String> fetchQuestionsJson(String url) {
        String body = restClient.get().uri(url).retrieve().body(String.class);
        return CompletableFuture.completedFuture(body);
    }

    @Async
    public CompletableFuture<String> fetchAnswersJson(String url) {
        String body = restClient.get().uri(url).retrieve().body(String.class);
        return CompletableFuture.completedFuture(body);
    }
}
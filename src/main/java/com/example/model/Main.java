package com.example.model;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Answer a1 = new Answer("Paris", true);
        Answer a2 = new Answer("London", false);
        Answer a3 = new Answer("Berlin", false);

        Question q1 = new Question(
                "What is the capital of France?",
                List.of(a1, a2, a3)
        );

        Quiz quiz = new Quiz(
                "Geography Quiz",
                List.of(q1)
        );

        QuizSerializer.saveQuiz(quiz, "quiz.json");
        Quiz loadedQuiz = QuizSerializer.loadQuiz("quiz.json");

        Scanner input = new Scanner(System.in);
        int score = 0;

        for (IQuestion q : loadedQuiz.getQuestions()) {
            System.out.println(q.getQuestionText());

            List<IAnswer> answers = q.getAnswers();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i).getText());
            }

            int choice = input.nextInt() - 1;
            if (answers.get(choice).isCorrect()) {
                score++;
            }
        }

        System.out.println("Score: " + score);
    }
}

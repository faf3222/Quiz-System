package com.example.model;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class QuizSerializer {

    private static final Gson gson = new Gson();

    public static void saveQuiz(Quiz quiz, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(quiz, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Quiz loadQuiz(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, Quiz.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.example.model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class QuizFxApp extends Application {


    private IQuiz quiz;
    private int index = 0;
    private int score = 0;

    private Label questionLabel;
    private ToggleGroup group;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private Button nextButton;

    @Override
    public void start(Stage stage) throws Exception {

        quiz = createQuiz();

        questionLabel = new Label();
        questionLabel.setStyle("-fx-font-size: 16px");

        group = new ToggleGroup();
        r1 = new RadioButton();
        r2 = new RadioButton();
        r3 = new RadioButton();
        r1.setToggleGroup(group);
        r2.setToggleGroup(group);
        r3.setToggleGroup(group);

        nextButton = new Button("Next");
        nextButton.setOnAction(e -> next(stage));

        VBox box = new VBox(10, questionLabel, r1, r2, r3, nextButton);
        box.setPadding(new Insets(20));
        box.setAlignment(Pos.CENTER_LEFT);

        loadQuestion();

        Scene scene = new Scene(box, 420, 300);
        stage.setTitle("Quiz App");
        stage.setScene(scene);
        stage.show();
    }


    private IQuiz createQuiz() {

        Answer a1 = new Answer("Paris", true);
        Answer a2 = new Answer("London", false);
        Answer a3 = new Answer("Berlin", false);
        java.util.List<Answer> answers1 = new java.util.ArrayList<>();
        answers1.add(a1);
        answers1.add(a2);
        answers1.add(a3);
        Question q1 = new Question("What is the capital of France?", answers1);

        Answer b1 = new Answer("3", false);
        Answer b2 = new Answer("4", true);
        Answer b3 = new Answer("5", false);
        java.util.List<Answer> answers2 = new java.util.ArrayList<>();
        answers2.add(b1);
        answers2.add(b2);
        answers2.add(b3);
        Question q2 = new Question("What is 2 + 2?", answers2);

        Answer c1 = new Answer("Blue Whale", true);
        Answer c2 = new Answer("Elephant", false);
        Answer c3 = new Answer("Giraffe", false);
        java.util.List<Answer> answers3 = new java.util.ArrayList<>();
        answers3.add(c1);
        answers3.add(c2);
        answers3.add(c3);
        Question q3 = new Question("Largest animal on Earth?", answers3);

        java.util.List<Question> questions = new java.util.ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        return new Quiz("General Knowledge Quiz", questions);
    }


    private void loadQuestion() {
        IQuestion q = quiz.getQuestions().get(index);
        questionLabel.setText(q.getQuestionText());

        java.util.List<IAnswer> answers = q.getAnswers();
        r1.setText(answers.get(0).getText());
        r2.setText(answers.get(1).getText());
        r3.setText(answers.get(2).getText());

        group.selectToggle(null);

        if (index == quiz.getQuestions().size() - 1) {
            nextButton.setText("Finish");
        } else {
            nextButton.setText("Next");
        }
    }

    private void next(Stage stage) {
        Toggle selected = group.getSelectedToggle();
        if (selected == null) {
            return;
        }

        int chosenIndex = selected == r1 ? 0 : selected == r2 ? 1 : 2;

        IQuestion q = quiz.getQuestions().get(index);
        if (q.getAnswers().get(chosenIndex).isCorrect()) {
            score++;
        }

        index++;

        if (index < quiz.getQuestions().size()) {
            loadQuestion();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quiz Finished");
            alert.setHeaderText("Your Score");
            alert.setContentText(score + " / " + quiz.getQuestions().size());
            alert.showAndWait();
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

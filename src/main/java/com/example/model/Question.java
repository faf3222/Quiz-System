package com.example.model;

import java.util.List;

public class Question implements IQuestion {

    private String questionText;
    private List<Answer> answers;

    public Question(String questionText, List<Answer> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<IAnswer> getAnswers() {
        return List.copyOf(answers);
    }


    public boolean isCorrectAnswer(int index) {
        if (index < 0 || index >= answers.size()) {
            return false;
        }
        return answers.get(index).isCorrect();
    }
}

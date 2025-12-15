package com.example.model;

import java.util.List;

public interface IQuestion {
    String getQuestionText();
    List<IAnswer> getAnswers();
}

package com.surveyapi.models;

public class AnsweredQuestion extends Question {
    private String answer;

    public AnsweredQuestion() {
    }

    public AnsweredQuestion(String description, String[] options, String answer) {
        super(description, options);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

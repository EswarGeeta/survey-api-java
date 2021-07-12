package com.surveyapi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.Arrays;

class Question {
    private String description;
    private String[] options;

    public Question() {

    }

    public Question(String description, String[] options) {
        this.description = description;
        this.options = options;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", options=" + Arrays.toString(options) +
                '}';
    }
}

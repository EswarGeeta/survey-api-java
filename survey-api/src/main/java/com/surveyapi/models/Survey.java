package com.surveyapi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Arrays;

@DynamoDbBean
@DynamoDBTable(tableName = "surveys")
public class Survey {
    private int surveyId;
    private String name;
    private Question questions;

    public Survey() {
    }

    public Survey(int survey_id, String name, Question questions) {
        this.surveyId = survey_id;
        this.name = name;
        this.questions = questions;

    }

    @DynamoDBHashKey
    public int getSurveyId() {
        return surveyId;
    }
    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    @DynamoDbConvertedBy(QuestionConverter.class)
    public Question getQuestions() {
        System.out.println("Going to get the questions from survey object/string");
        return questions;
    }
    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "survey_id=" + surveyId +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }

}

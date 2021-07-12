package com.surveyapi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionConverter implements DynamoDBTypeConverter<String, Question> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
        public String convert(Question object) {
            Question question = (Question) object;
        try {
            return objectMapper.writeValueAsString(question);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

        @Override
        public Question unconvert(String s) {
            Question qeustion = null;
            try {
                qeustion = objectMapper.readValue(s, Question.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return qeustion;
        }
    }
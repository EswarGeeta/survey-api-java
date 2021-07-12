package com.surveyapi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import javax.management.Attribute;

public class QuestionConverter implements AttributeConverter<Question> {
    private final ObjectMapper objectMapper = new ObjectMapper();

/*
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
*/

    @Override
    public AttributeValue transformFrom(Question question) {
        try {
            return AttributeValue.builder().n(objectMapper.writeValueAsString(question)).build();
//            return objectMapper.writeValueAsString(question);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Question transformTo(AttributeValue attributeValue) {
        try {
            return objectMapper.readValue(attributeValue.toString(), Question.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EnhancedType<Question> type() {
        return EnhancedType.of(Question.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.B;
    }
}
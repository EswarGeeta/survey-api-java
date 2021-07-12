package com.surveyapi.lambdahandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.surveyapi.models.Survey;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedResource;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.http.apache.internal.impl.ApacheSdkHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * Handler for requests to Lambda function.
 */
public class CreateSurveyHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.create();

    DynamoDbClient dynamoDbClient = DynamoDbClient.create();
    DynamoDbEnhancedClient enhancedClient =
            DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(dynamoDbClient)
                    .build();
//    DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.create(new ApacheSdkHttpClient("something"));

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        Survey survey = null;
        try {
            survey = objectMapper.readValue(input.getBody(), Survey.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return response
                    .withBody("{}")
                    .withStatusCode(500);
        }

        DynamoDbTable<Survey> customerTable =
                enhancedClient.table("surveys", TableSchema.fromBean(Survey.class));
        customerTable.putItem(survey);
/*
        Table table = dynamoDB.getTable(System.getenv("SURVEYS_TABLE"));
        Item item = new Item().withPrimaryKey("survey_id", survey.getSurveyId())
                .withString("name", survey.getName())
                .withList("questions", survey.getQuestions());
*/
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Survey created");
    }

    public static void main(String[] args) {

    }

}

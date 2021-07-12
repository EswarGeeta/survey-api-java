package com.surveyapi.models;

import java.time.LocalDateTime;

public class PublishedSurvey {

    private int client_id;
    private String client_name;
    private String email;

    private SurveyWithAnswers surveyWithAnswers;
    private int otp;
    private LocalDateTime otpExpirationTime;
    private boolean answered;
}

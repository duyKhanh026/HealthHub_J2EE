package com.healthhub.hospital.service;

import com.google.cloud.recaptchaenterprise.v1.RecaptchaEnterpriseServiceClient;
import com.google.recaptchaenterprise.v1.Assessment;
import com.google.recaptchaenterprise.v1.CreateAssessmentRequest;
import com.google.recaptchaenterprise.v1.Event;
import com.google.recaptchaenterprise.v1.ProjectName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RecaptchaService {

    @Value("${google.cloud.project-id}")
    private String projectID;

    @Value("${google.recaptcha.key}")
    private String recaptchaKey;

    public float createAssessment(String token, String recaptchaAction) throws IOException {
        try (RecaptchaEnterpriseServiceClient client = RecaptchaEnterpriseServiceClient.create()) {
            Event event = Event.newBuilder().setSiteKey(recaptchaKey).setToken(token).build();
            CreateAssessmentRequest createAssessmentRequest =
                    CreateAssessmentRequest.newBuilder()
                            .setParent(ProjectName.of(projectID).toString())
                            .setAssessment(Assessment.newBuilder().setEvent(event).build())
                            .build();

            Assessment response = client.createAssessment(createAssessmentRequest);

            if (!response.getTokenProperties().getValid()) {
                throw new IllegalArgumentException("Invalid token: " + response.getTokenProperties().getInvalidReason().name());
            }

            if (!response.getTokenProperties().getAction().equals(recaptchaAction)) {
                throw new IllegalArgumentException("Unexpected action: " + response.getTokenProperties().getAction());
            }
            return response.getRiskAnalysis().getScore();
        }
    }
}

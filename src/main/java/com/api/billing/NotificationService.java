package com.api.billing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class NotificationService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    @Value("${teams.webhook.url}")
    private String teamsWebhookUrl;

    /**
     * 메시지를 특정 플랫폼으로 전송.
     *
     * @param message          보낼 메시지
     * @param notificationType 플랫폼 타입 (SLACK, TEAMS)
     */
    public void sendMessage(String message, NotificationType notificationType) {
        try {
            // JSON 포맷 생성
            String payload = "{\"text\": \"" + message + "\"}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(payload, headers);

            String webhookUrl = getWebhookUrl(notificationType);

            restTemplate.postForEntity(webhookUrl, entity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 플랫폼 타입에 따라 웹훅 URL을 반환.
     *
     * @param notificationType 플랫폼 타입
     * @return 웹훅 URL
     */
    private String getWebhookUrl(NotificationType notificationType) {
        switch (notificationType) {
            case SLACK:
                return slackWebhookUrl;
            case TEAMS:
                return teamsWebhookUrl;
            default:
                throw new IllegalArgumentException("Unsupported notification type: " + notificationType);
        }
    }
}

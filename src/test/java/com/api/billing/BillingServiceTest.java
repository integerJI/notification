package com.api.billing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BillingServiceTest {

    @Autowired
    private BillingService billingService;

    // application.properties에서 웹훅 URL을 읽어옵니다.
    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    @Value("${teams.webhook.url}")
    private String teamsWebhookUrl;

    @Test
    public void testSendNotificationToSlack() {
        billingService.sendNotification("Testing Slack Notification", NotificationType.SLACK);

        assertNotNull(slackWebhookUrl, "Slack 웹훅 URL이 설정되어야 합니다.");
    }

    @Test
    public void testSendNotificationToTeams() {
        billingService.sendNotification("Testing Teams Notification", NotificationType.TEAMS);

        assertNotNull(teamsWebhookUrl, "Teams 웹훅 URL이 설정되어야 합니다.");
    }
}

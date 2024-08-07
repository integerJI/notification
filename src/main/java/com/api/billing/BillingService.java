package com.api.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    private final NotificationService notificationService;

    @Autowired
    public BillingService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(String message, NotificationType notificationType) {
        notificationService.sendMessage(message, notificationType);
    }
}

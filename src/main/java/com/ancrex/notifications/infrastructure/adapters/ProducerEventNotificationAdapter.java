package com.ancrex.notifications.infrastructure.adapters;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.domain.ports.eventnotification.output.ProducerEventNotification;
import com.ancrex.notifications.infrastructure.exception.SendEventNotificationException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ProducerEventNotificationAdapter implements ProducerEventNotification {

    public static final String ERROR_SENDING_NOTIFICATION = "Error sending notification";
    public static final String NOTIFICATION_SEND = "Notification send";
    private final String webhookUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public ProducerEventNotificationAdapter(@Value("${rest.notification.url}") String webhookUrl,
                                            RestTemplate restTemplate) {
        this.webhookUrl = webhookUrl;
        this.restTemplate = restTemplate;
    }

    @Retry(name = "webhookRetry", fallbackMethod = "fallbackNotification")
    @Override
    public Output apply(EventNotificationModel eventNotificationModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(eventNotificationModel, headers);

        try {
            restTemplate.postForObject(webhookUrl, request, String.class);
            return Output.builder()
                    .success(Boolean.TRUE)
                    .message(NOTIFICATION_SEND)
                    .build();
        } catch (Exception ex) {
            return Output.builder()
                    .success(Boolean.FALSE)
                    .message(ERROR_SENDING_NOTIFICATION)
                    .build();
        }
    }

    public Output fallbackNotification(EventNotificationModel eventNotificationModel, Exception ex) {
        //TODO: Send to DLQ
        log.error("Error sending notification: payload: {}, error: {}", eventNotificationModel, ex.getMessage(), ex);

        return Output.builder()
                .success(Boolean.FALSE)
                .message(ERROR_SENDING_NOTIFICATION)
                .build();

    }
}

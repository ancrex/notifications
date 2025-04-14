package com.ancrex.notifications.application.rest.model.response.eventnotification;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EventNotification {
    private String eventId;
    private String eventType;
    private String content;
    private LocalDateTime deliveryDate;
    private EventNotificationModel.DeliveryStatus deliveryStatus;
}

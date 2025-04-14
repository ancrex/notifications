package com.ancrex.notifications.infrastructure.entity;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EventNotificationEntity {
    private String eventId;
    private String eventType;
    private String content;
    private LocalDateTime deliveryDate;
    private EventNotificationModel.DeliveryStatus deliveryStatus;

}

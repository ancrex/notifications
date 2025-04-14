package com.ancrex.notifications.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EventNotificationModel {
    private String eventId;
    private String eventType;
    private String content;
    private LocalDateTime deliveryDate;
    private DeliveryStatus deliveryStatus;

    public enum DeliveryStatus {
        COMPLETED,
        FAILED
    }
}

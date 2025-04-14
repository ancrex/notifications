package com.ancrex.notifications.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventNotificationWrapper {
    private List<EventNotificationEntity> events;
}

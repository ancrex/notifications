package com.ancrex.notifications.application.rest.controller;


import com.ancrex.notifications.application.rest.model.response.eventnotification.EventNotification;
import com.ancrex.notifications.application.rest.model.response.eventnotification.EventNotificationResponse;
import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.domain.ports.eventnotification.input.GetEventNotificationById;
import com.ancrex.notifications.domain.ports.eventnotification.input.GetEventNotifications;
import com.ancrex.notifications.domain.ports.eventnotification.input.ReplayEventNotification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/notification_events")
@RequiredArgsConstructor
public class NotificationEventsController {

    private final ObjectMapper objectMapper;
    private final GetEventNotificationById getEventNotificationById;
    private final ReplayEventNotification replayEventNotification;
    private final GetEventNotifications getEventNotifications;


    @GetMapping("/{id}")
    public ResponseEntity<EventNotificationResponse> getEventNotificationById(
            @PathVariable String id) {
        return getEventNotificationById.apply(
                        GetEventNotificationById.Input
                                .builder()
                                .id(id)
                                .build()
                )
                .map(eventNotificationModel ->
                        ResponseEntity.ok(
                                objectMapper.convertValue(
                                        eventNotificationModel, EventNotificationResponse.class
                                )
                        )
                )
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<EventNotification>> getAllNotificationEvents(
            @RequestParam(value = "start_date") LocalDate startDate,
            @RequestParam(value = "end_date") LocalDate endDate,
            @RequestParam(value = "delivery_status") EventNotificationModel.DeliveryStatus deliveryStatus
    ) {


        final List<EventNotificationModel> eventNotificationList = getEventNotifications.apply(
                GetEventNotifications.Input.builder()
                        .deliveryStatus(deliveryStatus)
                        .startDate(startDate.atStartOfDay())
                        .endDate(endDate.atStartOfDay())
                        .build()
        );
        return ResponseEntity.ok(
                objectMapper.convertValue(eventNotificationList, new TypeReference<>() {
                })
        );

    }


    @PostMapping("/{id}/replay")
    public ResponseEntity<Void> replayNotificationById(@PathVariable String id) {
        replayEventNotification.accept(id);
        return ResponseEntity.ok().build();
    }
}

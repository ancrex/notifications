package com.ancrex.notifications.infrastructure.repository.eventnotification.impl;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.infrastructure.entity.EventNotificationEntity;
import com.ancrex.notifications.infrastructure.entity.EventNotificationWrapper;
import com.ancrex.notifications.infrastructure.repository.eventnotification.EventNotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventNotificationRepositoryImpl implements EventNotificationRepository {


    private static final String FILE_PATH = "src/main/resources/notification_events_db.json";
    private final Map<String, EventNotificationEntity> eventNotificationEntityMap = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    @Autowired
    public EventNotificationRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        loadData();
    }


    @Override
    public EventNotificationEntity save(EventNotificationEntity eventNotificationEntity) {
        return eventNotificationEntity;
    }

    @Override
    public List<EventNotificationEntity> getEventNotifications(LocalDateTime startDate,
                                                               LocalDateTime endDate,
                                                               EventNotificationModel.DeliveryStatus deliveryStatus) {

        return eventNotificationEntityMap.values().stream()
                .filter(
                        eventNotificationEntity ->
                                startDate.compareTo(eventNotificationEntity.getDeliveryDate()) <= 0
                                        && endDate.compareTo(eventNotificationEntity.getDeliveryDate()) >= 0
                                        && deliveryStatus.equals(eventNotificationEntity.getDeliveryStatus())
                ).toList();

    }

    @Override
    public Optional<EventNotificationEntity> getEventNotificationById(String id) {
        return Optional.ofNullable(
                eventNotificationEntityMap.get(id)
        );
    }


    private void loadData() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                final EventNotificationWrapper eventNotificationWrapper =
                        objectMapper.readValue(file, EventNotificationWrapper.class);

                eventNotificationWrapper
                        .getEvents()
                        .forEach(eventNotificationEntity ->
                                eventNotificationEntityMap
                                        .put(eventNotificationEntity.getEventId(), eventNotificationEntity)
                        );
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading data.", e);
        }
    }
}

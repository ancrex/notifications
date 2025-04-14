package com.ancrex.notifications.infrastructure.adapters;


import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.domain.ports.eventnotification.output.PersistenceEventNotification;
import com.ancrex.notifications.infrastructure.entity.EventNotificationEntity;
import com.ancrex.notifications.infrastructure.repository.eventnotification.EventNotificationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersistenceEventNotificationFileAdapter implements PersistenceEventNotification {

    private final EventNotificationRepository eventNotificationRepository;
    private final ObjectMapper objectMapper;

    @Override
    public EventNotificationModel save(EventNotificationModel eventNotificationModel) {
        final EventNotificationEntity eventNotificationEntity =
                objectMapper.convertValue(eventNotificationModel, EventNotificationEntity.class);

        return objectMapper.convertValue(
                eventNotificationRepository.save(eventNotificationEntity),
                EventNotificationModel.class);
    }

    @Override
    public List<EventNotificationModel> getEventNotifications(LocalDateTime startDate, LocalDateTime endDate,
                                                              EventNotificationModel.DeliveryStatus deliveryStatus) {
        final List<EventNotificationEntity> eventNotificationList =
                eventNotificationRepository.getEventNotifications(startDate, endDate, deliveryStatus);

        return objectMapper.convertValue(eventNotificationList, new TypeReference<>() {
        });
    }

    @Override
    public Optional<EventNotificationModel> getEventNotificationById(String id) {
        return eventNotificationRepository.getEventNotificationById(id)
                .map(eventNotificationEntity ->
                        objectMapper.convertValue(eventNotificationEntity, EventNotificationModel.class));
    }
}

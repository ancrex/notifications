package com.ancrex.notifications.infrastructure.repository.eventnotification;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.infrastructure.entity.EventNotificationEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventNotificationRepository {
    EventNotificationEntity save(EventNotificationEntity eventModel);

    List<EventNotificationEntity> getEventNotifications(LocalDateTime startDate, LocalDateTime endDate,
                                                        EventNotificationModel.DeliveryStatus deliveryStatus);

    Optional<EventNotificationEntity> getEventNotificationById(String id);


}

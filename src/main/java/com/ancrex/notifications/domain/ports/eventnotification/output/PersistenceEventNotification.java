package com.ancrex.notifications.domain.ports.eventnotification.output;

import com.ancrex.notifications.domain.model.EventNotificationModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PersistenceEventNotification {

    EventNotificationModel save(EventNotificationModel eventModel);

    List<EventNotificationModel> getEventNotifications(LocalDateTime startDate, LocalDateTime endDate,
                                                       EventNotificationModel.DeliveryStatus deliveryStatus);

    Optional<EventNotificationModel> getEventNotificationById(String id);

}

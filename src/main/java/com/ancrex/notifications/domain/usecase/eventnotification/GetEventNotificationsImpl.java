package com.ancrex.notifications.domain.usecase.eventnotification;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.domain.ports.eventnotification.input.GetEventNotifications;
import com.ancrex.notifications.domain.ports.eventnotification.output.PersistenceEventNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GetEventNotificationsImpl implements GetEventNotifications {

    private final PersistenceEventNotification persistenceEventNotification;

    @Override
    public List<EventNotificationModel> apply(Input input) {

        return persistenceEventNotification.getEventNotifications(
                input.getStartDate(), input.getEndDate(), input.getDeliveryStatus());
    }
}

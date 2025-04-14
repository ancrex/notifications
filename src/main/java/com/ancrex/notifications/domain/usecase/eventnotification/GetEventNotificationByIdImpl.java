package com.ancrex.notifications.domain.usecase.eventnotification;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.domain.ports.eventnotification.input.GetEventNotificationById;
import com.ancrex.notifications.domain.ports.eventnotification.output.PersistenceEventNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class GetEventNotificationByIdImpl implements GetEventNotificationById {

    private final PersistenceEventNotification persistenceEventNotification;

    @Override
    public Optional<EventNotificationModel> apply(Input input) {
        return persistenceEventNotification.getEventNotificationById(input.getId());
    }
}

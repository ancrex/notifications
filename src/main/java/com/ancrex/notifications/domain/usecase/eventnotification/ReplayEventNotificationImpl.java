package com.ancrex.notifications.domain.usecase.eventnotification;

import com.ancrex.notifications.domain.exception.EventNotificationNotFoundException;
import com.ancrex.notifications.domain.model.EventNotificationModel;
import com.ancrex.notifications.domain.ports.eventnotification.input.ReplayEventNotification;
import com.ancrex.notifications.domain.ports.eventnotification.output.PersistenceEventNotification;
import com.ancrex.notifications.domain.ports.eventnotification.output.ProducerEventNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class ReplayEventNotificationImpl implements ReplayEventNotification {

    private static final String EVENT_NOTIFICATION_NOT_FOUND = "Event notification not found";
    private final PersistenceEventNotification persistenceEventNotification;
    private final ProducerEventNotification producerEventNotification;

    @Override
    public void accept(String id) {
        persistenceEventNotification.getEventNotificationById(id)
                .ifPresentOrElse(this::sendAndUpdate,
                        () -> {
                            throw new EventNotificationNotFoundException(EVENT_NOTIFICATION_NOT_FOUND);
                        }
                );
    }

    private void sendAndUpdate(EventNotificationModel eventNotificationModel) {
        ProducerEventNotification.Output result =
                producerEventNotification.apply(eventNotificationModel);

        persistenceEventNotification.save(
                eventNotificationModel
                        .toBuilder()
                        .deliveryStatus(getNewStatus(result))
                        .deliveryDate(LocalDateTime.now())
                        .build());

    }

    private EventNotificationModel.DeliveryStatus getNewStatus(ProducerEventNotification.Output result) {

        if (result.success()) {
            return EventNotificationModel.DeliveryStatus.COMPLETED;
        }

        return EventNotificationModel.DeliveryStatus.FAILED;
    }
}

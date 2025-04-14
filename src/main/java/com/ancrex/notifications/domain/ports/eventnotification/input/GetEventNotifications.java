package com.ancrex.notifications.domain.ports.eventnotification.input;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public interface GetEventNotifications extends
        Function<GetEventNotifications.Input, List<EventNotificationModel>> {

    @Builder
    @Getter
    @AllArgsConstructor
    class Input {
        private final EventNotificationModel.DeliveryStatus deliveryStatus;
        private final LocalDateTime startDate;
        private final LocalDateTime endDate;
    }

}

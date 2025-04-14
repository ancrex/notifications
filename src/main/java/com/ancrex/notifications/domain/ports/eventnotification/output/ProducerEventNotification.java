package com.ancrex.notifications.domain.ports.eventnotification.output;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import lombok.Builder;

import java.util.function.Function;

public interface ProducerEventNotification
        extends Function<EventNotificationModel, ProducerEventNotification.Output> {

    @Builder
    record Output(boolean success, String message) {}
}

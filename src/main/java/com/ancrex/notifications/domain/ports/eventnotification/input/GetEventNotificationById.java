package com.ancrex.notifications.domain.ports.eventnotification.input;

import com.ancrex.notifications.domain.model.EventNotificationModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;
import java.util.function.Function;

public interface GetEventNotificationById
        extends Function<GetEventNotificationById.Input, Optional<EventNotificationModel>> {


    @Builder
    @Getter
    @AllArgsConstructor
    class Input {
        private final String id;
    }

}

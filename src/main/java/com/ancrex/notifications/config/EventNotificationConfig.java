package com.ancrex.notifications.config;

import com.ancrex.notifications.domain.ports.eventnotification.input.GetEventNotificationById;
import com.ancrex.notifications.domain.ports.eventnotification.input.GetEventNotifications;
import com.ancrex.notifications.domain.ports.eventnotification.input.ReplayEventNotification;
import com.ancrex.notifications.domain.ports.eventnotification.output.PersistenceEventNotification;
import com.ancrex.notifications.domain.ports.eventnotification.output.ProducerEventNotification;
import com.ancrex.notifications.domain.usecase.eventnotification.GetEventNotificationByIdImpl;
import com.ancrex.notifications.domain.usecase.eventnotification.GetEventNotificationsImpl;
import com.ancrex.notifications.domain.usecase.eventnotification.ReplayEventNotificationImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventNotificationConfig {

    @Bean
    public GetEventNotificationById getEventNotificationById(PersistenceEventNotification persistenceEventNotification) {
        return new GetEventNotificationByIdImpl(persistenceEventNotification);
    }

    @Bean
    public GetEventNotifications getEventNotifications(PersistenceEventNotification persistenceEventNotification) {
        return new GetEventNotificationsImpl(persistenceEventNotification);
    }

    @Bean
    public ReplayEventNotification replayEventNotification(PersistenceEventNotification persistenceEventNotification,
                                                           ProducerEventNotification producerEventNotification) {
        return new ReplayEventNotificationImpl(persistenceEventNotification, producerEventNotification);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}

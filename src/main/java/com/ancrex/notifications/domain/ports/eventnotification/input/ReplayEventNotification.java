package com.ancrex.notifications.domain.ports.eventnotification.input;

import java.util.function.Consumer;


/**
 * Re-send event notification by id
 */
public interface ReplayEventNotification extends Consumer<String> {
}

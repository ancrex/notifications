package com.ancrex.notifications.infrastructure.exception;

public class SendEventNotificationException extends RuntimeException {
    public SendEventNotificationException(String message) {
        super(message);
    }

    public SendEventNotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}

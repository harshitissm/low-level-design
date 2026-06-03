package model;

import enums.LogLevel;

import java.time.LocalDateTime;

public class LogMessage {

    private final String applicationName;
    private final String message;
    private final LogLevel level;
    private final LocalDateTime timestamp;

    public LogMessage(String applicationName, String message, LogLevel level) {
        this.applicationName = applicationName;
        this.message = message;
        this.level = level;
        this.timestamp = LocalDateTime.now();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
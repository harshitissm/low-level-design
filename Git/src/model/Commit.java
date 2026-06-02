package model;

import java.time.LocalDateTime;

public class Commit {

    private final String id;
    private final String message;
    private final LocalDateTime timestamp;
    private final Commit parent;

    public Commit(String id, String message, Commit parent) {
        this.id = id;
        this.message = message;
        this.parent = parent;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Commit getParent() {
        return parent;
    }
}
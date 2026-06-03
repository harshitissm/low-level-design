package model;

import java.util.UUID;

public class Message {

    private final String id;
    private final String payload;
    private final long timestamp;

    public Message(String payload) {
        this.id = UUID.randomUUID().toString();
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return payload;
    }
}
package model;

import store.InMemoryMessageStore;
import store.MessageStore;

import java.util.List;
import java.util.UUID;

public class Topic {

    private final String id;
    private final String name;
    private final MessageStore store;

    public Topic(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.store = new InMemoryMessageStore();
    }

    public String getName() {
        return name;
    }

    public void publish(Message message) {
        store.append(message);
    }

    public List<Message> getMessages() {
        return store.getMessages();
    }
}
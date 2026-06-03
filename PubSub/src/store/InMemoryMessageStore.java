package store;

import model.Message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryMessageStore implements MessageStore {

    private final List<Message> messages = new CopyOnWriteArrayList<>();

    @Override
    public void append(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }
}
package store;

import model.Message;

import java.util.List;

public interface MessageStore {
    void append(Message message);
    List<Message> getMessages();
}
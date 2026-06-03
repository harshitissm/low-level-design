package consumer;

import model.Message;

public interface Consumer {
    String getId();
    void consume(Message message) throws Exception;
}
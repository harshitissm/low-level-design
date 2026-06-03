package consumer;

import model.Message;

public class PrintConsumer implements Consumer {

    private final String id;

    public PrintConsumer(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) {
        System.out.println("[" + id + "] Consumed -> " + message.getPayload());
    }
}
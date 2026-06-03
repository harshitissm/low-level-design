package service;

import model.Message;
import model.Subscription;
import model.Topic;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void dispatch(Topic topic, Subscription subscription) {
        executor.submit(() -> {

            List<Message> messages = topic.getMessages();

            while(subscription.getOffset() < messages.size()) {

                int currentOffset = subscription.getOffset();

                Message message = messages.get(currentOffset);

                try {
                    if(subscription.shouldConsume(message)) {
                        subscription.getConsumer().consume(message);
                    }

                    subscription.advanceOffset();
                } catch (Exception e) {
                    System.out.println("Consumer unavailable. Retry later.");
                    break;
                }
            }
        });
    }
}
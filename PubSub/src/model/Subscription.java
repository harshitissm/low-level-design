package model;

import consumer.Consumer;
import filter.MessageFilter;

import java.util.concurrent.atomic.AtomicInteger;

public class Subscription {

    private final Consumer consumer;

    private final MessageFilter filter;

    private final AtomicInteger offset = new AtomicInteger(0);

    public Subscription(Consumer consumer, MessageFilter filter) {
        this.consumer = consumer;
        this.filter = filter;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public int getOffset() {
        return offset.get();
    }

    public void advanceOffset() {
        offset.incrementAndGet();
    }

    public boolean shouldConsume(Message message) {
        return filter == null || filter.matches(message);
    }
}
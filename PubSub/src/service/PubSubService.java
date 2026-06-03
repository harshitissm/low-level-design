package service;

import consumer.Consumer;
import filter.MessageFilter;
import model.Message;
import model.Subscription;
import model.Topic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class PubSubService {

    private final TopicManager topicManager = new TopicManager();

    private final Dispatcher dispatcher = new Dispatcher();

    private final Map<String, List<Subscription>> subscriptions = new ConcurrentHashMap<>();

    public Topic createTopic(String name) {
        subscriptions.put(name, new CopyOnWriteArrayList<>());
        return topicManager.createTopic(name);
    }

    public void subscribe(String topicName, Consumer consumer, MessageFilter filter) {
        Subscription subscription = new Subscription(consumer, filter);
        subscriptions.get(topicName).add(subscription);
    }

    public void unsubscribe(String topicName, String consumerId) {
        subscriptions
                .get(topicName)
                .removeIf(s -> s.getConsumer().getId().equals(consumerId));
    }

    public void publish(String topicName, String payload) {
        Topic topic = topicManager.getTopic(topicName);
        Message message = new Message(payload);

        topic.publish(message);

        List<Subscription> topicSubs = subscriptions.get(topicName);

        for(Subscription sub : topicSubs) {
            dispatcher.dispatch(topic, sub);
        }
    }
}
package service;

import model.Topic;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {

    private final Map<String, Topic> topics = new ConcurrentHashMap<>();

    public Topic createTopic(String name) {
        Topic topic = new Topic(name);
        topics.put(name, topic);
        return topic;
    }

    public Topic getTopic(String name) {
        Topic topic = topics.get(name);
        if (topic == null) {
            throw new RuntimeException("Topic not found");
        }
        return topic;
    }

    public Collection<Topic> getAllTopics() {
        return topics.values();
    }
}
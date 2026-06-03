package filter;

import model.Message;

public class EventTypeFilter implements MessageFilter {

    private final String keyword;

    public EventTypeFilter(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean matches(Message message) {
        return message.getPayload().contains(keyword);
    }
}
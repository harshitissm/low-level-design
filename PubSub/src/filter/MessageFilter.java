package filter;

import model.Message;

public interface MessageFilter {
    boolean matches(Message message);
}
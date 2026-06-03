package appenders;

import model.LogMessage;

public class HttpAppender implements Appender {

    private final String endpoint;
    private final int port;

    public HttpAppender(String endpoint, int port) {
        this.endpoint = endpoint;
        this.port = port;
    }

    @Override
    public void append(LogMessage message) {
        System.out.println("[HTTP][" + endpoint + ":" + port + "] " + format(message));
    }

    private String format(LogMessage message) {
        return String.format(
                "%s [%s] [%s] %s",
                message.getTimestamp(),
                message.getApplicationName(),
                message.getLevel(),
                message.getMessage()
        );
    }
}
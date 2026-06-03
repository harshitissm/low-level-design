package appenders;

import model.LogMessage;

public class DBAppender implements Appender {

    private final String username;
    private final String password;
    private final int port;

    public DBAppender(String username, String password, int port) {
        this.username = username;
        this.password = password;
        this.port = port;
    }

    @Override
    public void append(LogMessage message) {
        System.out.println("[DB][" + port + "] " + format(message));
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
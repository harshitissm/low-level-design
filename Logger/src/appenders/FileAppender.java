package appenders;

import model.LogMessage;

public class FileAppender implements Appender {

    private final String filePath;

    public FileAppender(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void append(LogMessage message) {
        System.out.println("[FILE][" + filePath + "] " + format(message));
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
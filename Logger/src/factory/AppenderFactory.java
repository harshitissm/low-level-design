package factory;

import appenders.Appender;
import appenders.DBAppender;
import appenders.FileAppender;
import appenders.HttpAppender;
import config.AppenderConfig;

public class AppenderFactory {

    public Appender createAppender(AppenderConfig config) {
        switch (config.getTarget().toLowerCase()) {
            case "file":
                return new FileAppender(config.getFilePath());

            case "db":
                return new DBAppender(config.getUsername(), config.getPassword(), config.getPort());

            case "http":
                return new HttpAppender(config.getEndpoint(), config.getPort());

            default:
                throw new IllegalArgumentException("Unsupported target");
        }
    }
}
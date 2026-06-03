package model;

import appenders.Appender;
import config.AppenderConfig;
import enums.LogLevel;
import factory.AppenderFactory;
import processor.AsyncLogProcessor;
import processor.LogProcessor;
import router.LogRouter;

public class LoggerBootstrap {

    public static AppenderConfig dbConfig;
    public static AppenderConfig fileConfig;

    public static Logger create() {
        LogRouter router = new LogRouter();

        AppenderFactory factory = new AppenderFactory();

        Appender dbAppender = factory.createAppender(dbConfig);

        Appender fileAppender = factory.createAppender(fileConfig);

        router.register(LogLevel.INFO, dbAppender);
        router.register(LogLevel.DEBUG, fileAppender);

        LogProcessor processor = new AsyncLogProcessor(router);

        return new Logger("PAYMENT_SERVICE", processor);
    }
}
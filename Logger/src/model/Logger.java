package model;

import enums.LogLevel;
import processor.LogProcessor;

public class Logger {

    private final String appName;
    private final LogProcessor processor;

    public Logger(String appName, LogProcessor processor) {
        this.appName = appName;
        this.processor = processor;
    }

    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    public void info(String msg) {
        log(LogLevel.INFO, msg);
    }

    public void warn(String msg) {
        log(LogLevel.WARN, msg);
    }

    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    private void log(LogLevel level, String msg) {
        processor.process(new LogMessage(appName, msg, level));
    }
}
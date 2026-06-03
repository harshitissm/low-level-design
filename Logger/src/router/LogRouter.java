package router;

import appenders.Appender;
import enums.LogLevel;
import model.LogMessage;

import java.util.*;

public class LogRouter {

    private final Map<LogLevel, List<Appender>> routes = new HashMap<>();

    public void register(LogLevel level, Appender appender) {
        routes.computeIfAbsent(level,k -> new ArrayList<>()).add(appender);
    }

    public void route(LogMessage message) {
        List<Appender> appenders = routes.getOrDefault(message.getLevel(), Collections.emptyList());
        for (Appender appender : appenders) {
            appender.append(message);
        }
    }
}
package appenders;

import model.LogMessage;

public interface Appender {

    void append(LogMessage message);

}
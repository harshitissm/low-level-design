package processor;

import model.LogMessage;

public interface LogProcessor {
    void process(LogMessage message);
}
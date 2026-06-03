package processor;

import model.LogMessage;
import router.LogRouter;

public class SyncLogProcessor implements LogProcessor {

    private final LogRouter router;

    public SyncLogProcessor(LogRouter router) {
        this.router = router;
    }

    @Override
    public void process(LogMessage message) {
        router.route(message);
    }
}
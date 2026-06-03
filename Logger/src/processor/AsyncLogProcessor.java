package processor;

import model.LogMessage;
import router.LogRouter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncLogProcessor implements LogProcessor {

    private final LogRouter router;
    private final BlockingQueue<LogMessage> queue = new LinkedBlockingQueue<>();

    public AsyncLogProcessor(LogRouter router) {
        this.router = router;
        startWorker();
    }

    @Override
    public void process(LogMessage message) {
        queue.offer(message);
    }

    private void startWorker() {
        Thread worker = new Thread(() -> {
            while (true) {
                try {
                    LogMessage message = queue.take();
                    router.route(message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        worker.setDaemon(true);
        worker.start();
    }
}
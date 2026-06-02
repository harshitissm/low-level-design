package entities;

import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket {

    private final long capacity;
    private final long refillTokensPerSec;
    private double availableTokens;
    private long lastRefillTimeMillis;

    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucket(long capacity, long refillTokensPerSec) {
        this.capacity = capacity;
        this.refillTokensPerSec = refillTokensPerSec;
        this.availableTokens = capacity;
        this.lastRefillTimeMillis = System.currentTimeMillis();
    }

    public boolean tryConsumeToken() {
        lock.lock();
        try {
            refillTokens();
            if (availableTokens < 1) {
                return false;
            }
            availableTokens--;
            return true;
        } finally {
            lock.unlock();
        }
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        long elapsedTimeMillis = now - lastRefillTimeMillis;
        if (elapsedTimeMillis <= 0) {
            return;
        }
        long tokensToAdd = (elapsedTimeMillis / 1000) * refillTokensPerSec;
        availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
        lastRefillTimeMillis = now;
    }
}

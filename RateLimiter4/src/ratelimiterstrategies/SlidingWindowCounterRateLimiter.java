package ratelimiterstrategies;

import entities.RateLimiter;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter implements RateLimiter {
    private final long limit;
    private final long windowMillis;

    private long currentWindowStart;

    private long currentCount;
    private long previousCount;

    public SlidingWindowCounterRateLimiter(long limit, long windowSeconds) {
        this.limit = limit;
        this.windowMillis = windowSeconds * 1000;
        this.currentWindowStart = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
        long elapsed = now - currentWindowStart;

        if (elapsed >= windowMillis) {
            previousCount = currentCount;
            currentCount = 0;

            currentWindowStart = now - (elapsed % windowMillis);

            elapsed = now - currentWindowStart;
        }

        double weight = 1.0 - ((double) elapsed / windowMillis);

        double estimatedCount = previousCount * weight + currentCount;

        if (estimatedCount >= limit) {
            return false;
        }

        currentCount++;

        return true;
    }
}

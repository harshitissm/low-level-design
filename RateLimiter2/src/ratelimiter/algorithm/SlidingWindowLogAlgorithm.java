package ratelimiter.algorithm;

import ratelimiter.model.AlgorithmType;
import ratelimiter.model.RateLimitDecision;
import ratelimiter.model.TierPolicy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public final class SlidingWindowLogAlgorithm implements RateLimitingAlgorithm {
    private static final class WindowState {
        private final Deque<Long> timestamps = new ArrayDeque<>();
        private final ReentrantLock lock = new ReentrantLock();
    }

    private final Map<String, WindowState> states = new ConcurrentHashMap<>();

    @Override
    public RateLimitDecision allow(String key, long timestampInSeconds, TierPolicy policy) {
        WindowState state = states.computeIfAbsent(key, unused -> new WindowState());
        int maxRequests = policy.getMaxRequests();
        int windowInSeconds = policy.getWindowInSeconds();
        long windowStart = timestampInSeconds - windowInSeconds + 1;

        state.lock.lock();
        try {
            while (!state.timestamps.isEmpty() && state.timestamps.peekFirst() < windowStart) {
                state.timestamps.pollFirst();
            }

            if (state.timestamps.size() >= maxRequests) {
                long oldest = state.timestamps.peekFirst();
                long retryAfter = (oldest + windowInSeconds) - timestampInSeconds;
                return new RateLimitDecision(
                        false,
                        0,
                        Math.max(1, retryAfter),
                        AlgorithmType.SLIDING_WINDOW_LOG,
                        "sliding-window-limit-exceeded"
                );
            }

            state.timestamps.offerLast(timestampInSeconds);
            int remaining = maxRequests - state.timestamps.size();
            return new RateLimitDecision(
                    true,
                    remaining,
                    0,
                    AlgorithmType.SLIDING_WINDOW_LOG,
                    "allowed"
            );
        } finally {
            state.lock.unlock();
        }
    }
}

package ratelimiter.algorithm;

import ratelimiter.model.AlgorithmType;
import ratelimiter.model.RateLimitDecision;
import ratelimiter.model.TierPolicy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public final class TokenBucketAlgorithm implements RateLimitingAlgorithm {
    private static final class BucketState {
        private double tokens;
        private long lastRefillTimestamp;
        private final ReentrantLock lock = new ReentrantLock();

        BucketState(double tokens, long lastRefillTimestamp) {
            this.tokens = tokens;
            this.lastRefillTimestamp = lastRefillTimestamp;
        }
    }

    private final Map<String, BucketState> states = new ConcurrentHashMap<>();

    @Override
    public RateLimitDecision allow(String key, long timestampInSeconds, TierPolicy policy) {
        int capacity = policy.getCapacity();
        double refillPerSecond = policy.getRefillTokensPerSecond();

        BucketState state = states.computeIfAbsent(
                key,
                unused -> new BucketState(capacity, timestampInSeconds)
        );

        state.lock.lock();
        try {
            long elapsed = Math.max(0L, timestampInSeconds - state.lastRefillTimestamp);
            if (elapsed > 0) {
                double refill = elapsed * refillPerSecond;
                state.tokens = Math.min(capacity, state.tokens + refill);
                state.lastRefillTimestamp = timestampInSeconds;
            }

            if (state.tokens >= 1.0) {
                state.tokens -= 1.0;
                return new RateLimitDecision(
                        true,
                        (int) Math.floor(state.tokens),
                        0,
                        AlgorithmType.TOKEN_BUCKET,
                        "allowed"
                );
            }

            double needed = 1.0 - state.tokens;
            long retryAfter = (long) Math.ceil(needed / refillPerSecond);
            return new RateLimitDecision(
                    false,
                    0,
                    Math.max(1, retryAfter),
                    AlgorithmType.TOKEN_BUCKET,
                    "token-bucket-limit-exceeded"
            );
        } finally {
            state.lock.unlock();
        }
    }
}

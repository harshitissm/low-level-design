package ratelimiter.service;

import ratelimiter.algorithm.RateLimitingAlgorithm;
import ratelimiter.algorithm.SlidingWindowLogAlgorithm;
import ratelimiter.algorithm.TokenBucketAlgorithm;
import ratelimiter.clock.Clock;
import ratelimiter.model.AlgorithmType;
import ratelimiter.model.RateLimitDecision;
import ratelimiter.model.TierPolicy;
import ratelimiter.model.UserTier;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class RateLimiterService {
    private final Clock clock;
    private final Map<UserTier, TierPolicy> tierPolicies = new ConcurrentHashMap<>();
    private final Map<String, UserTier> userTierMapping = new ConcurrentHashMap<>();
    private final Map<AlgorithmType, RateLimitingAlgorithm> algorithms = new ConcurrentHashMap<>();

    public RateLimiterService(Clock clock) {
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
        algorithms.put(AlgorithmType.SLIDING_WINDOW_LOG, new SlidingWindowLogAlgorithm());
        algorithms.put(AlgorithmType.TOKEN_BUCKET, new TokenBucketAlgorithm());
    }

    public void setTierPolicy(UserTier tier, TierPolicy policy) {
        tierPolicies.put(
                Objects.requireNonNull(tier, "tier must not be null"),
                Objects.requireNonNull(policy, "policy must not be null")
        );
    }

    public void setUserTier(String userId, UserTier tier) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId must not be null/blank");
        }
        userTierMapping.put(userId, Objects.requireNonNull(tier, "tier must not be null"));
    }

    public RateLimitDecision allow(String userId, long timestampInSeconds) {
        UserTier tier = userTierMapping.getOrDefault(userId, UserTier.NORMAL);
        TierPolicy policy = tierPolicies.get(tier);
        if (policy == null) {
            throw new IllegalStateException("No rate-limit policy configured for tier: " + tier);
        }

        RateLimitingAlgorithm algorithm = algorithms.get(policy.getAlgorithmType());
        if (algorithm == null) {
            throw new IllegalStateException("No algorithm registered for: " + policy.getAlgorithmType());
        }

        String key = tier + ":" + userId;
        return algorithm.allow(key, timestampInSeconds, policy);
    }

    public RateLimitDecision allow(String userId) {
        return allow(userId, clock.nowSeconds());
    }
}

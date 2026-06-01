package ratelimiter.algorithm;

import ratelimiter.model.RateLimitDecision;
import ratelimiter.model.TierPolicy;

public interface RateLimitingAlgorithm {
    RateLimitDecision allow(String key, long timestampInSeconds, TierPolicy policy);
}

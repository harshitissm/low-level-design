package ratelimiterstrategies;

import ratelimitkey.RateLimitKey;

public class SlidingWindowLogAlgorithm implements RateLimitAlgorithm {
    @Override
    public boolean allowRequest(RateLimitKey key) {
        return false;
    }
}

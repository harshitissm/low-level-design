package ratelimiterstrategies;

import ratelimitkey.RateLimitKey;

public interface RateLimitAlgorithm {
    boolean allowRequest(RateLimitKey key);
}

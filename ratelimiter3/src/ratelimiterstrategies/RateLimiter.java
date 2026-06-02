package ratelimiterstrategies;

import ratelimitkey.RateLimitKey;

public interface RateLimiter {
    boolean allowRequest(RateLimitKey key);
}

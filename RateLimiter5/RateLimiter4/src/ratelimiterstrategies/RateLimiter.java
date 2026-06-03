package ratelimiterstrategies;

public interface RateLimiter {
    boolean allowRequest(String key);  // key = userId, IP, apiKey, or plan name depending on context
}

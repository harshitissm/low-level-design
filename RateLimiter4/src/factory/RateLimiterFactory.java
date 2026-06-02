package factory;

import entities.RateLimiter;
import ratelimiterstrategies.FixedWindowRateLimiter;
import ratelimiterstrategies.LeakyBucketRateLimiter;
import ratelimiterstrategies.SlidingWindowRateLimiter;
import ratelimiterstrategies.SlidingWindowCounterRateLimiter;
import ratelimiterstrategies.TokenBucketRateLimiter;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(String type, int maxRequests, long windowSizeMillis) {
        if (maxRequests <= 0 || windowSizeMillis <= 0) {
            throw new IllegalArgumentException("maxRequests and windowSizeMillis must be > 0");
        }
        return switch (type) {
            case "fixed" -> new FixedWindowRateLimiter(maxRequests, windowSizeMillis);
            case "sliding" -> new SlidingWindowRateLimiter(maxRequests, windowSizeMillis);
            case "sliding-window-counter" ->
                    new SlidingWindowCounterRateLimiter(maxRequests, windowSizeMillis);
            case "token-bucket" ->
                    new TokenBucketRateLimiter(maxRequests, (1.0 * maxRequests / windowSizeMillis * 1000));
            case "leaky-bucket" ->
                    new LeakyBucketRateLimiter(maxRequests, Math.max(1L, windowSizeMillis / maxRequests));
            default -> throw new IllegalArgumentException("Unsupported type.");
        };
    }
}

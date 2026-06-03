package services;

import ratelimiterstrategies.RateLimiter;
import entities.RateLimiterHandler;
import entities.RequestContext;
import factory.RateLimiterFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {

    // ---- Dynamic chain-based API ----
    // Each user has their own chain, configured at registration time.
    private final Map<String, RateLimiterHandler> userChains = new ConcurrentHashMap<>();

    public void registerPolicy(String userId, RateLimiterHandler chain) {
        userChains.put(userId, chain);
    }

    public boolean allowRequest(String userId, RequestContext ctx) {
        RateLimiterHandler chain = userChains.get(userId);
        if (chain == null) throw new IllegalArgumentException("No policy registered for: " + userId);
        return chain.handle(ctx);
    }

    // ---- Legacy userId-only API (unchanged) ----
    private final Map<String, RateLimiter> userRateLimiters = new ConcurrentHashMap<>();

    public void registerUser(String userId, String algorithm, int maxRequests, long windowSizeSeconds) {
        userRateLimiters.put(userId, RateLimiterFactory.createRateLimiter(algorithm, maxRequests, windowSizeSeconds * 1000));
    }

    public boolean allowRequest(String userId) {
        RateLimiter rateLimiter = userRateLimiters.get(userId);
        if (rateLimiter == null) throw new IllegalArgumentException("User not registered");
        return rateLimiter.allowRequest(userId);
    }
}

package factory;

import entities.Plan;
import ratelimiterstrategies.RateLimiter;
import entities.RateLimiterHandler;
import handlers.ApiKeyRateLimiterHandler;
import handlers.IpRateLimiterHandler;
import handlers.UserRateLimiterHandler;

public class PolicyBuilder {

    public static RateLimiterHandler byUserId(String algorithm, int maxRequests, long windowSizeSeconds) {
        RateLimiter limiter = RateLimiterFactory.createRateLimiter(algorithm, maxRequests, windowSizeSeconds * 1000);
        return new UserRateLimiterHandler(limiter);
    }

    public static RateLimiterHandler byIp(String algorithm, int maxRequests, long windowSizeSeconds) {
        RateLimiter limiter = RateLimiterFactory.createRateLimiter(algorithm, maxRequests, windowSizeSeconds * 1000);
        return new IpRateLimiterHandler(limiter);
    }

    public static RateLimiterHandler byApiKey(String algorithm, int maxRequests, long windowSizeSeconds) {
        RateLimiter limiter = RateLimiterFactory.createRateLimiter(algorithm, maxRequests, windowSizeSeconds * 1000);
        return new ApiKeyRateLimiterHandler(limiter);
    }

    // User gets their own bucket; the bucket size is determined by their plan
    public static RateLimiterHandler byUserWithPlan(Plan plan) {
        RateLimiter limiter = RateLimiterFactory.createRateLimiter(
                plan.getDefaultStrategy(),
                plan.getMaxRequests(),
                plan.getWindowSizeSeconds() * 1000);
        return new UserRateLimiterHandler(limiter);
    }

    // IP check first, then userId — both must pass
    public static RateLimiterHandler byIpAndUserId(
            String ipAlgo,   int ipMax,   long ipWindowSecs,
            String userAlgo, int userMax, long userWindowSecs) {
        RateLimiter ipLimiter   = RateLimiterFactory.createRateLimiter(ipAlgo,   ipMax,   ipWindowSecs   * 1000);
        RateLimiter userLimiter = RateLimiterFactory.createRateLimiter(userAlgo, userMax, userWindowSecs * 1000);
        IpRateLimiterHandler head = new IpRateLimiterHandler(ipLimiter);
        head.setNext(new UserRateLimiterHandler(userLimiter));
        return head;
    }

    // API key check first, then userId — both must pass
    public static RateLimiterHandler byApiKeyAndUserId(
            String apiKeyAlgo, int apiKeyMax, long apiKeyWindowSecs,
            String userAlgo,   int userMax,   long userWindowSecs) {
        RateLimiter apiKeyLimiter = RateLimiterFactory.createRateLimiter(apiKeyAlgo, apiKeyMax, apiKeyWindowSecs * 1000);
        RateLimiter userLimiter   = RateLimiterFactory.createRateLimiter(userAlgo,   userMax,   userWindowSecs   * 1000);
        ApiKeyRateLimiterHandler head = new ApiKeyRateLimiterHandler(apiKeyLimiter);
        head.setNext(new UserRateLimiterHandler(userLimiter));
        return head;
    }

    // Full chain: IP → API key → userId — all three must pass
    public static RateLimiterHandler byAll(
            String ipAlgo,     int ipMax,     long ipWindowSecs,
            String apiKeyAlgo, int apiKeyMax, long apiKeyWindowSecs,
            String userAlgo,   int userMax,   long userWindowSecs) {
        RateLimiter ipLimiter     = RateLimiterFactory.createRateLimiter(ipAlgo,     ipMax,     ipWindowSecs     * 1000);
        RateLimiter apiKeyLimiter = RateLimiterFactory.createRateLimiter(apiKeyAlgo, apiKeyMax, apiKeyWindowSecs * 1000);
        RateLimiter userLimiter   = RateLimiterFactory.createRateLimiter(userAlgo,   userMax,   userWindowSecs   * 1000);
        IpRateLimiterHandler head = new IpRateLimiterHandler(ipLimiter);
        head.setNext(new ApiKeyRateLimiterHandler(apiKeyLimiter))
            .setNext(new UserRateLimiterHandler(userLimiter));
        return head;
    }
}

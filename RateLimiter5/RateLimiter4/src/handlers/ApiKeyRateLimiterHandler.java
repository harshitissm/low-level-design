package handlers;

import ratelimiterstrategies.RateLimiter;
import entities.RateLimiterHandler;
import entities.RequestContext;

public class ApiKeyRateLimiterHandler extends RateLimiterHandler {
    private final RateLimiter rateLimiter;

    public ApiKeyRateLimiterHandler(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean handle(RequestContext ctx) {
        if (ctx.getApiKey() == null) return passToNext(ctx);
        if (!rateLimiter.allowRequest(ctx.getApiKey())) return false;
        return passToNext(ctx);
    }
}

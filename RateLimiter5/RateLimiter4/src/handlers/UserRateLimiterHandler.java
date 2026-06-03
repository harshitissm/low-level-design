package handlers;

import ratelimiterstrategies.RateLimiter;
import entities.RateLimiterHandler;
import entities.RequestContext;

public class UserRateLimiterHandler extends RateLimiterHandler {
    private final RateLimiter rateLimiter;

    public UserRateLimiterHandler(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean handle(RequestContext ctx) {
        if (ctx.getUserId() == null) return passToNext(ctx);
        if (!rateLimiter.allowRequest(ctx.getUserId())) return false;
        return passToNext(ctx);
    }
}

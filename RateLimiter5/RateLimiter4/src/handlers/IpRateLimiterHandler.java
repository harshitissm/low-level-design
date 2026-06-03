package handlers;

import ratelimiterstrategies.RateLimiter;
import entities.RateLimiterHandler;
import entities.RequestContext;

public class IpRateLimiterHandler extends RateLimiterHandler {
    private final RateLimiter rateLimiter;

    public IpRateLimiterHandler(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean handle(RequestContext ctx) {
        if (ctx.getIpAddress() == null) return passToNext(ctx);
        if (!rateLimiter.allowRequest(ctx.getIpAddress())) return false;
        return passToNext(ctx);
    }
}

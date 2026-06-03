package entities;

public abstract class RateLimiterHandler {
    private RateLimiterHandler next;

    // Returns next so callers can chain: a.setNext(b).setNext(c)
    public RateLimiterHandler setNext(RateLimiterHandler next) {
        this.next = next;
        return next;
    }

    protected boolean passToNext(RequestContext ctx) {
        if (next == null) return true;
        return next.handle(ctx);
    }

    public abstract boolean handle(RequestContext ctx);
}

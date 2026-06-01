package ratelimiter.clock;

public final class SystemClock implements Clock {
    @Override
    public long nowSeconds() {
        return System.currentTimeMillis() / 1000L;
    }
}

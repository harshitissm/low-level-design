package config;

public class RateLimitConfig {

    private final long capacity;
    private final long refillTokensPerSec;

    public RateLimitConfig(long capacity, long refillTokensPerSec) {
        this.capacity = capacity;
        this.refillTokensPerSec = refillTokensPerSec;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getRefillTokensPerSec() {
        return refillTokensPerSec;
    }
}

package config;

import enums.AlgorithmType;

public class RateLimitConfig {

    private final long limit;
    private final long windowInSeconds;
    private final long capacity;
    private final long refillRate;
    private final AlgorithmType algorithmType;

    public RateLimitConfig(long limit, long windowInSeconds, long capacity, long refillRate, AlgorithmType algorithmType) {
        this.limit = limit;
        this.windowInSeconds = windowInSeconds;
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.algorithmType = algorithmType;
    }

    public long getLimit() {
        return limit;
    }

    public long getWindowInSeconds() {
        return windowInSeconds;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getRefillRate() {
        return refillRate;
    }

    public AlgorithmType getAlgorithmType() {
        return algorithmType;
    }
}

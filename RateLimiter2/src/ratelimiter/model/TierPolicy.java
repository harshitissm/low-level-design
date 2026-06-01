package ratelimiter.model;

public final class TierPolicy {
    private final AlgorithmType algorithmType;
    private final int maxRequests;
    private final int windowInSeconds;
    private final int capacity;
    private final double refillTokensPerSecond;

    private TierPolicy(
            AlgorithmType algorithmType,
            int maxRequests,
            int windowInSeconds,
            int capacity,
            double refillTokensPerSecond
    ) {
        this.algorithmType = algorithmType;
        this.maxRequests = maxRequests;
        this.windowInSeconds = windowInSeconds;
        this.capacity = capacity;
        this.refillTokensPerSecond = refillTokensPerSecond;
    }

    public static TierPolicy forSlidingWindow(int maxRequests, int windowInSeconds) {
        if (maxRequests <= 0 || windowInSeconds <= 0) {
            throw new IllegalArgumentException("maxRequests and windowInSeconds must be > 0");
        }
        return new TierPolicy(
                AlgorithmType.SLIDING_WINDOW_LOG,
                maxRequests,
                windowInSeconds,
                0,
                0.0
        );
    }

    public static TierPolicy forTokenBucket(int capacity, double refillTokensPerSecond) {
        if (capacity <= 0 || refillTokensPerSecond <= 0.0) {
            throw new IllegalArgumentException("capacity and refillTokensPerSecond must be > 0");
        }
        return new TierPolicy(
                AlgorithmType.TOKEN_BUCKET,
                0,
                0,
                capacity,
                refillTokensPerSecond
        );
    }

    public AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public int getWindowInSeconds() {
        return windowInSeconds;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRefillTokensPerSecond() {
        return refillTokensPerSecond;
    }
}

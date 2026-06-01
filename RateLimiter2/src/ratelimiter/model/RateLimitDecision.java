package ratelimiter.model;

public final class RateLimitDecision {
    private final boolean allowed;
    private final int remaining;
    private final long retryAfterSeconds;
    private final AlgorithmType algorithmType;
    private final String reason;

    public RateLimitDecision(
            boolean allowed,
            int remaining,
            long retryAfterSeconds,
            AlgorithmType algorithmType,
            String reason
    ) {
        this.allowed = allowed;
        this.remaining = remaining;
        this.retryAfterSeconds = retryAfterSeconds;
        this.algorithmType = algorithmType;
        this.reason = reason;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public int getRemaining() {
        return remaining;
    }

    public long getRetryAfterSeconds() {
        return retryAfterSeconds;
    }

    public AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "RateLimitDecision{" +
                "allowed=" + allowed +
                ", remaining=" + remaining +
                ", retryAfterSeconds=" + retryAfterSeconds +
                ", algorithmType=" + algorithmType +
                ", reason='" + reason + '\'' +
                '}';
    }
}

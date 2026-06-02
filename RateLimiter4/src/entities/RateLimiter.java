package entities;

public interface RateLimiter {
    boolean allowRequest(String userId);
}

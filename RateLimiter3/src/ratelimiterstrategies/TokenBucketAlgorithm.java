package ratelimiterstrategies;

import config.ConfigProvider;
import config.RateLimitConfig;
import entities.TokenBucket;
import ratelimitkey.RateLimitKey;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketAlgorithm implements RateLimitAlgorithm {

    private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final ConfigProvider configProvider;

    public TokenBucketAlgorithm(ConfigProvider configProvider) {
        this.configProvider = configProvider;
    }

    @Override
    public boolean allowRequest(RateLimitKey key) {
        TokenBucket bucket = buckets.computeIfAbsent(key.getKey(), k -> createBucket(key));
        return bucket.tryConsumeToken();
    }

    private TokenBucket createBucket(RateLimitKey key) {
        RateLimitConfig config = configProvider.getConfig(key);
        return new TokenBucket(config.getCapacity(), config.getRefillRate());
    }
}

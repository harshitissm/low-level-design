package factory;

import config.RateLimitConfig;
import ratelimiterstrategies.RateLimitAlgorithm;
import ratelimiterstrategies.SlidingWindowLogAlgorithm;
import ratelimiterstrategies.TokenBucketAlgorithm;

public class AlgorithmFactory {

    public RateLimitAlgorithm create(RateLimitConfig config) {

        return switch (config.getAlgorithmType()) {
            case TOKEN_BUCKET -> new TokenBucketAlgorithm(config);

            case FIXED_WINDOW -> new FixedWindowAlgorithm(config);

            case SLIDING_WINDOW_LOG -> new SlidingWindowLogAlgorithm(config);

            case LEAKY_BUCKET -> new LeakyBucketAlgorithm(config);

            case SLIDING_WINDOW_COUNTER -> new SlidingWindowCounterAlgorithm(config);
        };
    }
}

import config.ConfigProvider;
import config.ConfigProviderImpl;
import config.RateLimitConfig;
import ratelimiterstrategies.RateLimitAlgorithm;
import ratelimiterstrategies.TokenBucketAlgorithm;
import ratelimitkey.RateLimitKey;
import ratelimitkey.UserKey;
import services.UserService;

import java.util.Map;

public class Solution {

    public static void main(String[] args) {

        Map<String, RateLimitConfig> configs = Map.of(
                "USER:123", new RateLimitConfig(10, 1),
                "IP:1.1.1.1", new RateLimitConfig(50, 5)
        );

        UserService userService = new UserService();

        ConfigProvider provider = new ConfigProviderImpl(userService);

        RateLimitAlgorithm limiter = new TokenBucketAlgorithm(provider);

        RateLimitKey user = new UserKey("123");

        for (int i = 1; i <= 15; i++) {
            boolean allowed = limiter.allowRequest(user);
            System.out.println("Request " + i + " -> " + allowed);
        }
    }
}

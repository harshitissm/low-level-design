package config;

import entities.User;
import enums.AlgorithmType;
import ratelimitkey.RateLimitKey;
import services.UserService;

import java.util.HashMap;
import java.util.Map;

public class ConfigProviderImpl implements ConfigProvider {

    private final UserService userService;
    private final Map<String, RateLimitConfig> configs;

    public ConfigProviderImpl(UserService userService) {
        this.userService = userService;
        this.configs = new HashMap<>();
    }

    public void addNewConfig(RateLimitConfig config, RateLimitKey key) {
        configs.put(key.getKey(), config);
    }

    public RateLimitConfig getConfig(RateLimitKey key) {
        return configs.getOrDefault(
                key.getKey(),
                new RateLimitConfig(0, 0, 100, 2, AlgorithmType.TOKEN_BUCKET)
        );
    }

    public RateLimitConfig getConfigByPlan(RateLimitKey key) {
        String userId = key.getKey().replace("USER:", "");
        User user = userService.getUser(userId);
        return switch (user.getPlan()) {
            case FREE -> new RateLimitConfig(0, 0, 100, 2, AlgorithmType.TOKEN_BUCKET);
            case GOLD -> new RateLimitConfig(0, 0, 1000, 20, AlgorithmType.TOKEN_BUCKET);
            case PREMIUM -> new RateLimitConfig(0, 0, 5000, 100, AlgorithmType.TOKEN_BUCKET);
        };
    }
}

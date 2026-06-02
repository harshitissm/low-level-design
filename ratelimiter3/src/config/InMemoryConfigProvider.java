package config;

import ratelimitkey.RateLimitKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryConfigProvider implements ConfigProvider{

    private final ConcurrentHashMap<String, RateLimitConfig> configs;

    public InMemoryConfigProvider(ConcurrentHashMap<String, RateLimitConfig> configs) {
        this.configs = configs;
    }

    public RateLimitConfig getConfig(RateLimitKey key) {
        return configs.getOrDefault(key.getKey(), new RateLimitConfig(100, 2));
    }
}

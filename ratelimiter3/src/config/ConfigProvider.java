package config;

import ratelimitkey.RateLimitKey;

public interface ConfigProvider {

    RateLimitConfig getConfig(RateLimitKey key);

}

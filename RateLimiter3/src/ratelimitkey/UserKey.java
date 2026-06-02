package ratelimitkey;

public class UserKey implements RateLimitKey {

    private final String userId;

    public UserKey(String userId) {
        this.userId = userId;
    }

    @Override
    public String getKey() {
        return "USER:" + userId;
    }
}

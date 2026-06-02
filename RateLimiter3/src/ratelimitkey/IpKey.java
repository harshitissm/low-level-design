package ratelimitkey;

public class IpKey implements RateLimitKey {

    private final String ip;

    public IpKey(String ip) {
        this.ip = ip;
    }

    @Override
    public String getKey() {
        return "IP:" + ip;
    }
}

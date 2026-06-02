package ratelimitkey;

public class MerchantApiKey implements RateLimitKey{

    private final String merchantId;
    private final String api;

    public MerchantApiKey(String merchantId, String api) {
        this.merchantId = merchantId;
        this.api = api;
    }

    @Override
    public String getKey() {
        return "MERCHANT:" + merchantId + ":" + api;
    }
}

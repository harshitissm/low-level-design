package entities;

public enum Plan {
    FREE       (10,  60, "sliding"),
    PRO        (100, 60, "token-bucket"),
    ENTERPRISE (1000,60, "token-bucket");

    private final int maxRequests;
    private final long windowSizeSeconds;
    private final String defaultStrategy;

    Plan(int maxRequests, long windowSizeSeconds, String defaultStrategy) {
        this.maxRequests = maxRequests;
        this.windowSizeSeconds = windowSizeSeconds;
        this.defaultStrategy = defaultStrategy;
    }

    public int getMaxRequests()         { return maxRequests; }
    public long getWindowSizeSeconds()  { return windowSizeSeconds; }
    public String getDefaultStrategy()  { return defaultStrategy; }
}

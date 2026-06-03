package entities;

public class RequestContext {
    private final String userId;
    private final String ipAddress;
    private final String apiKey;
    private final Plan plan;

    public RequestContext(String userId, String ipAddress, String apiKey, Plan plan) {
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.apiKey = apiKey;
        this.plan = (plan != null) ? plan : Plan.FREE;
    }

    public String getUserId()    { return userId; }
    public String getIpAddress() { return ipAddress; }
    public String getApiKey()    { return apiKey; }
    public Plan   getPlan()      { return plan; }

    @Override
    public String toString() {
        return "RequestContext{userId='" + userId + "', ip='" + ipAddress
                + "', apiKey='" + apiKey + "', plan=" + plan + "}";
    }
}

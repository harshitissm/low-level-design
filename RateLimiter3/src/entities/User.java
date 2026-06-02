package entities;

import enums.Plan;

public class User {

    private final String userId;
    private final Plan plan;

    public User(String userId,
                Plan plan) {

        this.userId = userId;
        this.plan = plan;
    }

    public String getUserId() {
        return userId;
    }

    public Plan getPlan() {
        return plan;
    }
}

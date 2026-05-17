package loanentities;

public class CreditProfile {

    private final String userId;
    int creditScore;
    int salary;

    public CreditProfile(String userId, int salary) {
        this.userId = userId;
        this.salary = salary;
    }
}

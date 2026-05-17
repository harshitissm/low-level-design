package loanentities;

public class User {

    private final String id;
    private final String name;
    private final CreditProfile creditProfile;
    private String kycStatus;

    public User(String id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.creditProfile = new CreditProfile(id, salary);
    }

    public CreditProfile getCreditProfile() {
        return creditProfile;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }
}

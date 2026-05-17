package loanentities;

import loanentities.enums.LoanApplicationStatus;

public class LoanApplication {

    String id;
    String userId;

    double requestedAmount;
    int tenureMonths;

    LoanApplicationStatus status;


    public static class LoanApplicationBuilder {

    }

}

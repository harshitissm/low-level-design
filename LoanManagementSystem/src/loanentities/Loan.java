package loanentities;

import loanentities.enums.LoanStatus;

import java.util.List;

public class Loan {

    String id;
    String applicationId;
    String userId;

    double principalAmount;
    double interestRate;

    int tenureMonths;

    LoanStatus status;

    List<EMI> emis;

}

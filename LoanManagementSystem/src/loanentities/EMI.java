package loanentities;

import loanentities.enums.EMIStatus;

import java.util.Date;

public class EMI {

    String id;
    String loanId;
    int emiNo;
    double amount;
    Date dueDate;
    EMIStatus status;
    Payment payment;
}

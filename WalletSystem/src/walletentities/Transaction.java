package walletentities;

import walletentities.enums.TransactionType;

import java.util.Date;

public class Transaction {
    String id;
    String fromWalletId;
    String toWalletId;
    double amount;
    TransactionType type;
    String status;
    String idempotencyKey;
    Date createdAt;

    public static class TransactionBuilder {

    }

}

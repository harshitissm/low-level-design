package walletentities;

import walletentities.enums.Currency;
import walletentities.enums.WalletStatus;

public class Wallet {

    String id;
    String userId;
    double balance;
    Currency currency;
    WalletStatus status;
    int version; // for optimistic locking

}

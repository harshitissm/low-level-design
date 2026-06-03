import model.Logger;
import model.LoggerBootstrap;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerBootstrap.create();

        logger.info("Payment Successful");

        logger.debug("Debugging payment flow");

        logger.error("Transaction failed");
    }
}
package services;

import payment.PaymentStrategy;

public class PaymentService {
    private BookingService bookingService;

    public void processPayment(final String bookingId, final String userId,
                               final PaymentStrategy paymentStrategy) throws Exception {
        if(paymentStrategy.processPayment()){
            bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
        }else {
            processPaymentFailed(bookingId, user);
        }
    }
}

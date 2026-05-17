package services;

import entities.Booking;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {

    private final Map<Integer, Booking> bookings;
    private final AtomicInteger bookingCounter;

    public BookingService() {
        this.bookings = new HashMap<>();
        this.bookingCounter = new AtomicInteger(0);
    }
}

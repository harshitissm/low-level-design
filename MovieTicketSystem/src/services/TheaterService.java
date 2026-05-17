package services;

import entities.Screen;
import entities.Seat;
import entities.Theater;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TheaterService {

    private final Map<Integer, Theater> theaters;
    private final Map<Integer, Screen> screens;
    private final Map<Integer, Seat> seats;

    private final AtomicInteger theatreCounter;
    private final AtomicInteger screenCounter;
    private final AtomicInteger seatCounter;

    public TheaterService() {
        this.theaters = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
        this.theatreCounter = new AtomicInteger(0);
        this.screenCounter = new AtomicInteger(0);
        this.seatCounter = new AtomicInteger(0);
    }
}

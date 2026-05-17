package services;

import entities.Show;
import entities.ShowSeat;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockService {

    private final Integer lockTimeout;

    private final Map<Show, Set<ShowSeat>> locks;

    public SeatLockService(Integer lockTimeout) {
        this.locks = new ConcurrentHashMap<>();
        this.lockTimeout = lockTimeout;
    }

}

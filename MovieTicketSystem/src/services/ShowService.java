package services;

import entities.Show;

import java.util.HashMap;
import java.util.Map;

public class ShowService {

    private final Map<Integer, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }
}

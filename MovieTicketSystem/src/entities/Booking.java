package entities;

import java.util.Date;
import java.util.List;

public class Booking {

    int id;
    int showId;
    int userId;
    List<ShowSeat> bookedSeats;
    double totalAmount;
    Date createdAt;

}

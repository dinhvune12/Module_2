package services;

import models.Booking;
import java.util.List;

public interface IBookingService {
    void add();
    void display();
    List<Booking> getAll();
}

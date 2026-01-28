package models;

import utils.DateUtil;

import java.util.Comparator;

public class BookingComparator implements Comparator<Booking> {
    public int compare(Booking a, Booking b) {
        int c = DateUtil.parse(a.getStartDate()).compareTo(DateUtil.parse(b.getStartDate()));
        if (c != 0) return c;
        return a.getBookingId().compareTo(b.getBookingId());
    }
}

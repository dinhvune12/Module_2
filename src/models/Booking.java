package models;

public class Booking {
    private String bookingId;
    private String startDate;
    private String endDate;
    private String customerId;
    private String facilityId;

    public Booking() {}

    public Booking(String bookingId, String startDate, String endDate, String customerId, String facilityId) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.facilityId = facilityId;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public String toCsv() {
        return bookingId + "," + startDate + "," + endDate + "," + customerId + "," + facilityId;
    }

    public static Booking fromCsv(String line) {
        String[] p = line.split(",", -1);
        return new Booking(p[0], p[1], p[2], p[3], p[4]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking b = (Booking) o;
        return bookingId.equals(b.bookingId);
    }

    @Override
    public int hashCode() {
        return bookingId.hashCode();
    }

    @Override
    public String toString() {
        return "Booking{" + "id='" + bookingId + "', start='" + startDate + "', end='" + endDate +
                "', customerId='" + customerId + "', facilityId='" + facilityId + "'}";
    }
}

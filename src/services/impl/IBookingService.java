package services.impl;

import models.Booking;
import models.BookingComparator;
import models.Customer;
import models.Facility;
import services.ICustomerService;
import services.IFacilityService;
import utils.CsvUtil;
import utils.DateUtil;
import utils.InputUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class IBookingService implements services.IBookingService {

    private static final String PATH = "data/booking.csv";
    private static TreeSet<Booking> bookings = new TreeSet<>(new BookingComparator());

    private ICustomerService ICustomerService;
    private IFacilityService IFacilityService;

    public IBookingService(ICustomerService ICustomerService, IFacilityService IFacilityService) {
        this.ICustomerService = ICustomerService;
        this.IFacilityService = IFacilityService;
        load();
    }
    private void load() {
        bookings.clear();
        List<String> lines = CsvUtil.readLines(PATH);
        for (int i = 0; i < lines.size(); i++) {
            try {
                bookings.add(Booking.fromCsv(lines.get(i)));
            } catch (Exception e) {}
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Booking b : bookings) {
            lines.add(b.toCsv());
        }
        CsvUtil.writeLines(PATH, lines);
    }

    public void add() {
        load();
        System.out.println("\nCUSTOMERS:");
        List<Customer> customers = ICustomerService.getAll();
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
        String customerId = InputUtil.readLine("chon id khách đi: ");
        if (ICustomerService.findById(customerId) == null) {
            System.out.println("không tim thấy");
            return;
        }

        System.out.println("\nFACILITIES:");
        List<Facility> facilities = IFacilityService.getAllFacilities();
        for (int i = 0; i < facilities.size(); i++) {
            System.out.println(facilities.get(i));
        }
        String facilityId = InputUtil.readLine("chọn pacibilyty: ");
        if (IFacilityService.findById(facilityId) == null) {
            System.out.println("không tìm thấy");
            return;
        }
        String bookingId = InputUtil.readLine("id booking: ");
        String start = InputUtil.readLine("bắt đầu ngày (dd/MM/yyyy) ");
        String end = InputUtil.readLine("end date (dd/MM/yyyy) ");
        try {
            LocalDate s = DateUtil.parse(start);
            LocalDate e = DateUtil.parse(end);
            if (e.isBefore(s)) {
                System.out.println("kết thúc và bắt đầu phải ");
                return;
            }
        } catch (Exception ex) {
            System.out.println("ngày sai định dạng");
            return;
        }
        Booking b = new Booking(bookingId, start, end, customerId, facilityId);
        bookings.add(b);
        save();
        LocalDate now = LocalDate.now();
        LocalDate s = DateUtil.parse(start);
        if (s.getYear() == now.getYear() && s.getMonthValue() == now.getMonthValue()) {
            IFacilityService.increaseUsage(facilityId);
        }
        System.out.println("đặt");
    }
    public void display() {
        load();
        System.out.println("\nBOOKING LIST:");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    public List<Booking> getAll() {
        load();
        List<Booking> list = new ArrayList<>();
        for (Booking b : bookings) list.add(b);
        return list;
    }
}

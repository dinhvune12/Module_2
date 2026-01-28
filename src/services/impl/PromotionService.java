package services.impl;

import models.Booking;
import models.Customer;
import services.IBookingService;
import services.ICustomerService;
import utils.DateUtil;
import utils.InputUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;
public class PromotionService implements services.IPromotionService {

    private IBookingService IBookingService;
    private ICustomerService ICustomerService;
    public PromotionService(IBookingService IBookingService, ICustomerService ICustomerService) {
        this.IBookingService = IBookingService;
        this.ICustomerService = ICustomerService;
    }
    public void displayCustomersUseServiceByYear() {
        int year = InputUtil.readInt("năm: ", 1900, 2500);
        List<Booking> bookings = IBookingService.getAll();
        TreeSet<String> customerIds = new TreeSet<>();
        for (int i = 0; i < bookings.size(); i++) {
            try {
                LocalDate start = DateUtil.parse(bookings.get(i).getStartDate());
                if (start.getYear() == year) customerIds.add(bookings.get(i).getCustomerId());
            } catch (Exception e) {}
        }
        System.out.println("\nkhách hàng đăng kí đến " + year + ":");
        for (String id : customerIds) {
            Customer c = ICustomerService.findById(id);
            if (c != null) System.out.println(c);
        }
    }
    public void displayCustomersGetVoucher() {
        int v10 = InputUtil.readInt("Voucher 10% quantity: ", 0, 1000);
        int v20 = InputUtil.readInt("Voucher 20% quantity: ", 0, 1000);
        int v50 = InputUtil.readInt("Voucher 50% quantity: ", 0, 1000);
        int total = v10 + v20 + v50;
        if (total == 0) {
            System.out.println("No voucher.");
            return;
        }
        List<Booking> bookings = IBookingService.getAll();
        List<Booking> currentMonth = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (int i = 0; i < bookings.size(); i++) {
            try {
                LocalDate d = DateUtil.parse(bookings.get(i).getStartDate());
                if (d.getYear() == now.getYear() && d.getMonthValue() == now.getMonthValue()) {
                    currentMonth.add(bookings.get(i));
                }
            } catch (Exception e) {}
        }

        if (currentMonth.size() == 0) {
            System.out.println("No booking in current month.");
            return;
        }

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < currentMonth.size() && stack.size() < total; i++) {
            stack.push(currentMonth.get(i).getCustomerId());
        }

        System.out.println("\nVOUCHER LIST (late booking gets 10% first):");
        while (!stack.isEmpty()) {
            String cid = stack.pop();
            Customer c = ICustomerService.findById(cid);
            if (c == null) continue;
            String voucher;
            if (v10 > 0) { voucher = "10%"; v10--; }
            else if (v20 > 0) { voucher = "20%"; v20--; }
            else { voucher = "50%"; v50--; }
            System.out.println(c + " -> " + voucher);
        }
    }
}

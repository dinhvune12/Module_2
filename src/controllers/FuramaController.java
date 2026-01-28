package controllers;

import services.*;
import services.impl.PromotionService;
import utils.InputUtil;

public class FuramaController {

    private IEmployeeService IEmployeeService = new services.impl.IEmployeeService();
    private ICustomerService ICustomerService = new services.impl.ICustomerService();
    private IFacilityService IFacilityService = new services.impl.IFacilityService();
    private IBookingService IBookingService = new services.impl.IBookingService(ICustomerService, IFacilityService);
    private PromotionService PromotionService = new PromotionService(IBookingService, ICustomerService);

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n--- FURAMA RESORT ---");
            System.out.println("1. Employee Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Facility Management");
            System.out.println("4. Booking Management");
            System.out.println("5. Promotion Management");
            System.out.println("6. Exit");
            int choice = InputUtil.readInt("Choose: ", 1, 6);
            switch (choice) {
                case 1:
                    employeeMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    facilityMenu();
                    break;
                case 4:
                    bookingMenu();
                    break;
                case 5:
                    promotionMenu();
                    break;
                case 6:
                    System.out.println("Bye!");
                    return;
            }
        }
    }

    private void employeeMenu() {
        while (true) {
            System.out.println("\n--- EMPLOYEE MANAGEMENT ---");
            System.out.println("1. Display list employees");
            System.out.println("2. Add new employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Edit employee");
            System.out.println("5. Return main menu");
            int choice = InputUtil.readInt("Choose: ", 1, 5);
            switch (choice) {
                case 1:
                    IEmployeeService.display();
                    break;
                case 2:
                    IEmployeeService.add();
                    break;
                case 3:
                    IEmployeeService.delete();
                    break;
                case 4:
                    IEmployeeService.edit();
                    break;
                case 5:
                    return;
            }
        }
    }
    private void customerMenu() {
        while (true) {
            System.out.println("\n--- CUSTOMER MANAGEMENT ---");
            System.out.println("1. Display list customers");
            System.out.println("2. Add new customer");
            System.out.println("3. Edit customer");
            System.out.println("4. Return main menu");
            int choice = InputUtil.readInt("Choose: ", 1, 4);
            switch (choice) {
                case 1:
                    ICustomerService.display();
                    break;
                case 2:
                    ICustomerService.add();
                    break;
                case 3:
                    ICustomerService.edit();
                    break;
                case 4:
                    return;
            }
        }
    }
    private void facilityMenu() {
        while (true) {
            System.out.println("\n--- FACILITY MANAGEMENT ---");
            System.out.println("1. Display list facility");
            System.out.println("2. Add new facility");
            System.out.println("3. Display list facility maintenance");
            System.out.println("4. Return main menu");
            int choice = InputUtil.readInt("Choose: ", 1, 4);
            switch (choice) {
                case 1:
                    IFacilityService.display();
                    break;
                case 2:
                    addFacilityMenu();
                    break;
                case 3:
                    IFacilityService.displayMaintenance();
                    break;
                case 4:
                    return;
            }
        }
    }
    private void addFacilityMenu() {
        while (true) {
            System.out.println("\n--- ADD NEW FACILITY ---");
            System.out.println("1. Add New Villa");
            System.out.println("2. Add New Room");
            System.out.println("3. Back to menu");
            int choice = InputUtil.readInt("Choose: ", 1, 3);
            if (choice == 1) {
                IFacilityService.addVilla();
            } else if (choice == 2) {
                IFacilityService.addRoom();
            } else {
                return;
            }
        }
    }
    private void bookingMenu() {
        while (true) {
            System.out.println("\n--- BOOKING MANAGEMENT ---");
            System.out.println("1. Add new booking");
            System.out.println("2. Display list booking");
            System.out.println("3. Return main menu");
            int choice = InputUtil.readInt("Choose: ", 1, 3);
            switch (choice) {
                case 1:
                    IBookingService.add();
                    break;
                case 2:
                    IBookingService.display();
                    break;
                case 3:
                    return;
            }
        }
    }
    private void promotionMenu() {
        while (true) {
            System.out.println("\n--- PROMOTION MANAGEMENT ---");
            System.out.println("1. Display list customers use service");
            System.out.println("2. Display list customers get voucher");
            System.out.println("3. Return main menu");
            int choice = InputUtil.readInt("Choose: ", 1, 3);
            switch (choice) {
                case 1:
                    PromotionService.displayCustomersUseServiceByYear();
                    break;
                case 2:
                    PromotionService.displayCustomersGetVoucher();
                    break;
                case 3:
                    return;
            }
        }
    }
}

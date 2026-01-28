package services.impl;

import models.Customer;
import utils.CsvUtil;
import utils.InputUtil;
import utils.ValidateUtil;

import java.util.LinkedList;
import java.util.List;

public class ICustomerService implements services.ICustomerService {

    private static final String PATH = "data/customer.csv";
    private static List<Customer> customers = new LinkedList<>();

    static {
        load();
        if (customers.size() == 0) {
            customers.add(new Customer("C001", "Trang", "01/01/1999", "Female", "333333", "0900000003", "trang@gmail.com", "Gold", "Da Nang"));
            customers.add(new Customer("C002", "Nam", "02/02/1998", "Male", "444444", "0900000004", "nam@gmail.com", "Member", "Ha Noi"));
            save();
        }
    }

    private static void load() {
        customers.clear();
        List<String> lines = CsvUtil.readLines(PATH);
        for (int i = 0; i < lines.size(); i++) {
            try {
                customers.add(Customer.fromCsv(lines.get(i)));
            } catch (Exception e) {
                System.out.println("bỏ qua list");
            }
        }
    }

    private static void save() {
        List<String> lines = new LinkedList<>();
        for (int i = 0; i < customers.size(); i++) {
            lines.add(customers.get(i).toCsv());
        }
        CsvUtil.writeLines(PATH, lines);
    }

    public void display() {
        load();
        System.out.println("\nCUSTOMER LIST:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }
    }

    public void add() {
        load();
        String id = InputUtil.readLine("Id: ");
        String name = InputUtil.readLine("Name: ");
        String birthday = InputUtil.readLine("ngày sinh (dd/MM/yyyy): ");
        try {
            ValidateUtil.checkBirthday(birthday);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        String gender = InputUtil.readLine("giới tính: ");
        String idCard = InputUtil.readLine("id: ");
        String phone = InputUtil.readLine("sdt: ");
        String email = InputUtil.readLine("Email: ");
        String type = InputUtil.readLine("kiểu  ");
        String address = InputUtil.readLine("địa chỉ: ");
        customers.add(new Customer(id, name, birthday, gender, idCard, phone, email, type, address));
        save();

    }

    public void edit() {
        load();
        String id = InputUtil.readLine("id khách ");
        Customer found = findById(id);
        if (found == null) {
            System.out.println("không tìm thấy ");
            return;
        }
        String name = InputUtil.readLine("tên (" + found.getName() + "): ");
        if (name.length() > 0) found.setName(name);
        String address = InputUtil.readLine("vị trí (" + found.getAddress() + "): ");
        if (address.length() > 0) found.setAddress(address);
        save();
        System.out.println("Updated.");
    }

    public List<Customer> getAll() {
        load();
        return customers;
    }

    public Customer findById(String id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(id))

                return customers.get(i);
        }
        return null;
    }
}

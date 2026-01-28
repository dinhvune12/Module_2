package services.impl;

import exceptions.NotFoundException;
import models.Employee;
import utils.CsvUtil;
import utils.InputUtil;
import utils.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

public class IEmployeeService implements services.IEmployeeService {

    private static final String PATH = "data/employee.csv";
    private static List<Employee> employees = new ArrayList<>();

    static {
        load();
        if (employees.size() == 0) {
            employees.add(new Employee("E001", "An", "01/01/2005", "Male", "2356565", "0900000001", "an@gmail.com", "Dai hoc", "Le tan", 800));
            employees.add(new Employee("E002", "Binh", "02/02/2006", "Female", "222222", "0900000002", "binh@gmail.com", "Cao dang", "Quan ly", 1200));
            save();
        }
    }
    private static void load() {

        List<String> lines = CsvUtil.readLines(PATH);
        for (int i = 0; i < lines.size(); i++) {
            try {
                employees.add(Employee.fromCsv(lines.get(i)));
            } catch (Exception e) {
                System.out.println("Skip bad line in employee.csv");
            }
        }
    }

    private static void save() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            lines.add(employees.get(i).toCsv());
        }
        CsvUtil.writeLines(PATH, lines);
    }
    public void display() {
        load();
        System.out.println("\nEMPLOYEE LIST:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i));
        }
    }

    public void add() {
        load();
        String id = InputUtil.readLine("Id: ");
        String name = InputUtil.readLine("Name: ");
        String birthday = InputUtil.readLine("Birthday (dd/MM/yyyy): ");
        try { ValidateUtil.checkBirthday(birthday); } catch (Exception e) { System.out.println(e.getMessage()); return; }
        String gender = InputUtil.readLine("Gender: ");
        String idCard = InputUtil.readLine("IdCard: ");
        String phone = InputUtil.readLine("Phone: ");
        String email = InputUtil.readLine("Email: ");
        String level = InputUtil.readLine("Level: ");
        String position = InputUtil.readLine("Position: ");
        double salary = InputUtil.readDouble("Salary: ", 0);
        employees.add(new Employee(id, name, birthday, gender, idCard, phone, email, level, position, salary));
        save();
        System.out.println("Added.");
    }

    public void edit() {
        load();
        String id = InputUtil.readLine("Enter employee id to edit: ");
        Employee found = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                found = employees.get(i);
                break;
            }
        }
        if (found == null) {
            System.out.println("Not found.");
            return;
        }
        String name = InputUtil.readLine("Name (" + found.getName() + "): ");
        if (name.length() > 0) found.setName(name);
        String birthday = InputUtil.readLine("Birthday (" + found.getBirthday() + "): ");
        if (birthday.length() > 0) {
            try { ValidateUtil.checkBirthday(birthday); found.setBirthday(birthday); } catch (Exception e) { System.out.println(e.getMessage()); }
        }
        String phone = InputUtil.readLine("Phone (" + found.getPhone() + "): ");
        if (phone.length() > 0) found.setPhone(phone);
        save();
        System.out.println("Updated.");
    }

    public void delete() {
        load();
        String id = InputUtil.readLine("Enter employee id to delete: ");
        try {
            deleteById(id);
            save();
            System.out.println("Deleted.");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteById(String id) throws NotFoundException {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                employees.remove(i);
                return;
            }
        }
        throw new NotFoundException("Employee not found: " + id);
    }
}

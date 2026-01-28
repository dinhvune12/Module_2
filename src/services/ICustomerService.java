package services;

import models.Customer;
import java.util.List;

public interface ICustomerService {
    void display();
    void add();
    void edit();
    List<Customer> getAll();
    Customer findById(String id);
}

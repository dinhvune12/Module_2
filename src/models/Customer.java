package models;

public class Customer extends Person {
    private String customerType;
    private String address;

    public Customer() {}

    public Customer(String id, String name, String birthday, String gender, String idCard, String phone, String email,
                    String customerType, String address) {
        super(id, name, birthday, gender, idCard, phone, email);
        this.customerType = customerType;
        this.address = address;
    }
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String toCsv() {
        return super.toString() + "," + customerType + "," + address;
    }
    public static Customer fromCsv(String line) {
        String[] p = line.split(",", -1);
        return new Customer(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8]);
    }
    @Override
    public String toString() {
        return "Customer{" + "id='" + getId() + "', name='" + getName() + "', birthday='" + getBirthday() + "', gender='" + getGender() +
                "', idCard='" + getIdCard() + "', phone='" + getPhone() + "', email='" + getEmail() + "', type='" + customerType +
                "', address='" + address + "'}";
    }
}

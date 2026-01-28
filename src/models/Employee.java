package models;

public class Employee extends Person {
    private String level;
    private String position;
    private double salary;

    public Employee() {}

    public Employee(String id, String name, String birthday, String gender, String idCard, String phone, String email,
                    String level, String position, double salary) {
        super(id, name, birthday, gender, idCard, phone, email);
        this.level = level;
        this.position = position;
        this.salary = salary;
    }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String toCsv() {
        return super.toString() + "," + level + "," + position + "," + salary;
    }

    public static Employee fromCsv(String line) {
        String[] p = line.split(",", -1);
        return new Employee(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], Double.parseDouble(p[9]));
    }
    @Override
    public String toString() {
        return "Employee{" + "id='" + getId() + "', name='" + getName() + "', birthday='" + getBirthday() + "', gender='" + getGender() +
                "', idCard='" + getIdCard() + "', phone='" + getPhone() + "', email='" + getEmail() + "', level='" + level +
                "', position='" + position + "', salary=" + salary + "}";
    }
}

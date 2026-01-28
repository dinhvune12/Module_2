package models;

public abstract class Facility {
    private String id;
    private String serviceName;
    private double area;
    private double cost;
    private int maxPeople;
    private String rentType;

    public Facility() {}

    public Facility(String id, String serviceName, double area, double cost, int maxPeople, String rentType) {
        this.id = id;
        this.serviceName = serviceName;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentType = rentType;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public int getMaxPeople() { return maxPeople; }
    public void setMaxPeople(int maxPeople) { this.maxPeople = maxPeople; }

    public String getRentType() { return rentType; }
    public void setRentType(String rentType) { this.rentType = rentType; }

    @Override
    public String toString() {
        return "id='" + id + "', name='" + serviceName + "', area=" + area + ", cost=" + cost +
                ", maxPeople=" + maxPeople + ", rentType='" + rentType + "'";
    }

    public abstract String toCsv(int used);
}

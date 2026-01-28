package models;

public class Room extends Facility {
    private String freeService;

    public Room() {}

    public Room(String id, String serviceName, double area, double cost, int maxPeople, String rentType, String freeService) {
        super(id, serviceName, area, cost, maxPeople, rentType);
        this.freeService = freeService;
    }

    public String getFreeService() { return freeService; }
    public void setFreeService(String freeService)
    { this.freeService = freeService; }
    @Override
    public String toString() {
        return "Room{" + super.toString() + ", freeService='" + freeService + "'}";
    }

    @Override
    public String toCsv(int used) {
        return getId() + "," + getServiceName() + "," + getArea() + "," + getCost() + "," + getMaxPeople() + "," + getRentType() + "," +
                freeService + "," + used;
    }

    public static Room fromCsv(String line) {
        String[] p = line.split(",", -1);
        return new Room(p[0], p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]), Integer.parseInt(p[4]), p[5], p[6]);
    }
}

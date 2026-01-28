package models;

public class Villa extends Facility {
    private String roomStandard;
    private double poolArea;
    private int floors;

    public Villa() {}

    public Villa(String id, String serviceName, double area, double cost, int maxPeople, String rentType,
                 String roomStandard, double poolArea, int floors) {
        super(id, serviceName, area, cost, maxPeople, rentType);
        this.roomStandard = roomStandard;
        this.poolArea = poolArea;
        this.floors = floors;
    }

    public String getRoomStandard() { return roomStandard; }
    public void setRoomStandard(String roomStandard) { this.roomStandard = roomStandard; }

    public double getPoolArea() { return poolArea; }
    public void setPoolArea(double poolArea) { this.poolArea = poolArea; }

    public int getFloors() { return floors; }
    public void setFloors(int floors) { this.floors = floors; }

    @Override
    public String toString() {
        return "Villa{" + super.toString() + ", roomStandard='" + roomStandard + "', poolArea=" + poolArea + ", floors=" + floors + "}";
    }

    @Override
    public String toCsv(int used) {
        return getId() + "," + getServiceName() + "," + getArea() + "," + getCost() + "," + getMaxPeople() + "," + getRentType() + "," +
                roomStandard + "," + poolArea + "," + floors + "," + used;
    }

    public static Villa fromCsv(String line) {
        String[] p = line.split(",", -1);
        return new Villa(p[0], p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]), Integer.parseInt(p[4]), p[5],
                p[6], Double.parseDouble(p[7]), Integer.parseInt(p[8]));
    }
}

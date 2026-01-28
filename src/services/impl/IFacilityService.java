package services.impl;

import exceptions.ValidateException;
import models.Facility;
import models.Room;
import models.Villa;
import utils.CsvUtil;
import utils.InputUtil;
import utils.ValidateUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IFacilityService implements services.IFacilityService {

    private static final String VILLA_PATH = "data/villa.csv";
    private static final String ROOM_PATH = "data/room.csv";
    private static Map<Facility, Integer> facilities = new LinkedHashMap<>();
    static {
        load();
        if (facilities.size() == 0) {
            Villa v = new Villa("SVVL-0001", "Villa", 100, 500, 10, "Day", "VIP", 40, 2);
            Room r = new Room("SVRO-0001", "Room", 40, 100, 2, "Day", "Breakfast");
            facilities.put(v, 0);
            facilities.put(r, 0);
            save();
        }
    }
    private static void load() {
        facilities.clear();
        List<String> vLines = CsvUtil.readLines(VILLA_PATH);
        for (int i = 0; i < vLines.size(); i++) {
            try {
                String[] p = vLines.get(i).split(",", -1);
                Villa v = Villa.fromCsv(vLines.get(i));
                int used = Integer.parseInt(p[p.length - 1]);
                facilities.put(v, used);
            } catch (Exception e) {}
        }
        List<String> rLines = CsvUtil.readLines(ROOM_PATH);
        for (int i = 0; i < rLines.size(); i++) {
            try {
                String[] p = rLines.get(i).split(",", -1);
                Room r = Room.fromCsv(rLines.get(i));
                int used = Integer.parseInt(p[p.length - 1]);
                facilities.put(r, used);
            } catch (Exception e) {}
        }
    }

    private static void save() {
        List<String> villas = new ArrayList<>();
        List<String> rooms = new ArrayList<>();
        for (Facility f : facilities.keySet()) {
            int used = facilities.get(f);
            if (f instanceof Villa) villas.add(f.toCsv(used));
            if (f instanceof Room) rooms.add(f.toCsv(used));
        }
        CsvUtil.writeLines(VILLA_PATH, villas);
        CsvUtil.writeLines(ROOM_PATH, rooms);
    }

    public void display() {
        load();
        System.out.println("\nFACILITY LIST:");
        for (Facility f : facilities.keySet()) {
            System.out.println(f + " | used=" + facilities.get(f));
        }
    }

    public void addVilla() {
        load();
        try {
            String id = InputUtil.readLine("dịch vu id (SVVL-YYYY): ");
            ValidateUtil.checkServiceId(id, "VILLA");
            String name = InputUtil.readLine("dịch vụ tên: ");
            ValidateUtil.checkServiceName(name);
            double area = InputUtil.readDouble("khu vực là  (>30): ", 30);
            ValidateUtil.checkArea(area);
            double cost = InputUtil.readDouble("giá (>0): ", 0);
            int maxPeople = InputUtil.readInt("người max (1-19): ", 1, 19);
            ValidateUtil.checkMaxPeople(maxPeople);
            String rentType = InputUtil.readLine("kiểu thuế: ");
            ValidateUtil.checkServiceName(rentType);
            String standard = InputUtil.readLine("phong: ");
            ValidateUtil.checkServiceName(standard);
            double poolArea = InputUtil.readDouble("khu vục thấp (>30): ", 30);
            ValidateUtil.checkArea(poolArea);
            int floors = InputUtil.readInt("cửa (>0): ", 1, 1000);
            ValidateUtil.checkFloor(floors);

            Villa v = new Villa(id, name, area, cost, maxPeople, rentType, standard, poolArea, floors);
            facilities.put(v, 0);
            save();
            System.out.println("Added villa.");
        } catch (ValidateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRoom() {
        load();
        try {
            String id = InputUtil.readLine("dịch vu id (SVRO-YYYY): ");
            ValidateUtil.checkServiceId(id, "phong");
            String name = InputUtil.readLine("dịch v name: ");
            ValidateUtil.checkServiceName(name);
            double area = InputUtil.readDouble("khu vực (>30): ", 30);
            ValidateUtil.checkArea(area);
            double cost = InputUtil.readDouble("giá  (>0): ", 0);
            int maxPeople = InputUtil.readInt("người lớn nhât (1-19): ", 1, 19);
            ValidateUtil.checkMaxPeople(maxPeople);
            String rentType = InputUtil.readLine("kiểu thuế : ");
            ValidateUtil.checkServiceName(rentType);
            String free = InputUtil.readLine("dịch vụ free: ");
            Room r = new Room(id, name, area, cost, maxPeople, rentType, free);
            facilities.put(r, 0);
            save();
            System.out.println("Added room.");
        } catch (ValidateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayMaintenance() {
        load();
        System.out.println("\nMAINTENANCE LIST (used >=5):");
        for (Facility f : facilities.keySet()) {
            int used = facilities.get(f);
            if (used >= 5) System.out.println(f + " | used=" + used);
        }
    }

    public List<Facility> getAllFacilities() {
        load();
        List<Facility> list = new ArrayList<>();
        for (Facility f : facilities.keySet()) list.add(f);
        return list;
    }

    public Facility findById(String id) {
        load();
        for (Facility f : facilities.keySet()) {
            if (f.getId().equals(id)) return f;
        }
        return null;
    }

    public void increaseUsage(String facilityId) {
        load();
        for (Facility f : facilities.keySet()) {
            if (f.getId().equals(facilityId)) {
                facilities.put(f, facilities.get(f) + 1);
                save();
                return;
            }
        }
    }

    public void refreshUsageFromBookings() {
        load();
        save();
    }
}

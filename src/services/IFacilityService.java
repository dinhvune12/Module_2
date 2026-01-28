package services;

import models.Facility;
import java.util.List;

public interface IFacilityService {
    void display();
    void addVilla();
    void addRoom();
    void displayMaintenance();
    List<Facility> getAllFacilities();
    Facility findById(String id);
    void increaseUsage(String facilityId);
    void refreshUsageFromBookings();
}

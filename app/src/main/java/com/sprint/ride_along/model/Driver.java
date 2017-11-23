package com.sprint.ride_along.model;

/**
 *
 * @author Jorge A. Cano
 */
public class Driver {

    private int internalKey;
    private String name;
    private String studentId;
    private String carBrand;
    private String carColor;
    private String licensePlate;
    private String phonenumber;
    private int capacity;
    private String entryHour;
    private String exitHour;

    public Driver(int internalKey, String name, String studentId, String carBrand, String carColor, String licensePlate, String phonenumber, int capacity, String entryHour, String exitHour) {
        this.internalKey = internalKey;
        this.studentId = studentId;
        this.carBrand = carBrand;
        this.carColor = carColor;
        this.licensePlate = licensePlate;
        this.phonenumber = phonenumber;
        this.capacity = capacity;
        this.entryHour = entryHour;
        this.exitHour = exitHour;
        this.name = name;
    }

    
    
    /**
     * @return the internalKey
     */
    public int getInternalKey() {
        return internalKey;
    }

    /**
     * @return the studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @return the carBrand
     */
    public String getCarBrand() {
        return carBrand;
    }

    /**
     * @return the carColor
     */
    public String getCarColor() {
        return carColor;
    }

    /**
     * @return the licensePlate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the entryHour
     */
    public String getEntryHour() {
        return entryHour;
    }

    /**
     * @return the exitHour
     */
    public String getExitHour() {
        return exitHour;
    }

    public String getName() {
        return name;
    }
}

package com.example.rethink1.stock_prediction;

public class Manager {
    protected String employeeUID;
    protected String locationOfStore;


    public Manager(String employeeUID, String locationOfStore) {
        this.employeeUID = employeeUID;
        this.locationOfStore = locationOfStore;
    }

    public String getEmployeeUID() {
        return employeeUID;
    }

    public String getLocationOfStore() {
        return locationOfStore;
    }
}

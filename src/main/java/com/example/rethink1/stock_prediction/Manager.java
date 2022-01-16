package com.example.rethink1.stock_prediction;

import com.example.rethink1.stock_ordering.OrderLine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Scanner;

@Entity
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    protected long employeeUID;
    protected String locationOfStore;


    public Manager(long employeeUID, String locationOfStore) {
        this.employeeUID = employeeUID;
        this.locationOfStore = locationOfStore;
    }

    public Manager() {

    }

    public long getEmployeeUID() {
        return employeeUID;
    }

    public String getLocationOfStore() {
        return locationOfStore;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "employeeUID=" + employeeUID +
                ", locationOfStore='" + locationOfStore + '\'' +
                '}';
    }

    public boolean approve(OrderLine orderLine) {
        System.out.println("Approve order? (1) yes (0) no");
        System.out.println(orderLine.toString());

        Scanner sc = new Scanner(System.in);
        int approve = sc.nextInt();
        if (approve == 1) {
            return true;
        }
        else {
            return false;
        }
    }

}

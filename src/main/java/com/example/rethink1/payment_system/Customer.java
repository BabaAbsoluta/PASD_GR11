package com.example.rethink1.payment_system;

import com.example.rethink1.stock_ordering.Supplier;
import com.example.rethink1.stock_prediction.Product;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {

    @Id
    protected String customerID;
    protected String name;
    protected String password;
    protected double budget;
    protected boolean paymentMethod;

    public Customer (String customerID, String name, String password, double budget, boolean paymentMethod) {

        this.customerID = customerID;
        this.name = name;
        this.password = password;
        this.budget = budget ;
        this.paymentMethod = paymentMethod;
    }

}
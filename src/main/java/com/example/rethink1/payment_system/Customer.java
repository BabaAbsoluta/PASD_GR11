package com.example.rethink1.payment_system;

import com.example.rethink1.stock_prediction.ShoppingPortfolio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;
@Getter
@Setter
public class Customer implements Serializable {

    @Id
    protected String customerID;
    protected String name;
    protected String password;
    protected double budget;
    protected boolean paymentMethod;
    protected ShoppingPortfolio shoppingPortfolio;

    public Customer (String customerID, String name, String password, double budget, boolean paymentMethod) {
        this.customerID = customerID;
        this.name = name;
        this.password = password;
        this.budget = budget ;
        this.paymentMethod = paymentMethod;
    }

}
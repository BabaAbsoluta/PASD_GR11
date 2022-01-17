package com.example.rethink1.payment_system;

import com.example.rethink1.stock_ordering.Supplier;
import com.example.rethink1.stock_prediction.Product;

import java.util.List;

public class Customer {

    protected String customerID;
    protected String name;
    protected String password;
    protected List<Product> productList;
    protected double budget;
    protected boolean paymentMethod;
}

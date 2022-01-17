package com.example.rethink1.payment_system;

import com.example.rethink1.stock_prediction.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Basket {
    protected String basketUID;
    protected ArrayList<Product> products;
    protected String paymentMethod;
    protected LocalDate date;
    protected boolean paid;


    public Basket(ArrayList<Product> products, String paymentMethod, LocalDate date) {
        this.products = products;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }
}

package com.example.rethink1.stock_ordering;

import com.example.rethink1.stock_prediction.Product;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class VirtualBasket {
    protected ArrayList<Product> products;
    protected String paymentMethod;
    protected LocalDate date;

    public VirtualBasket(ArrayList<Product> products, String paymentMethod, LocalDate date) {
        this.products = products;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

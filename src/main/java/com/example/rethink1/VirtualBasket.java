package com.example.rethink1;

import com.example.rethink1.stock_prediction.Product;

import java.util.ArrayList;

public class VirtualBasket {
    protected ArrayList<Product> products;
    protected String paymentMethod;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

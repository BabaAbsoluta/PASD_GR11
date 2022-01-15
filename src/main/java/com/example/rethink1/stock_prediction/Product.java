package com.example.rethink1.stock_prediction;

import com.example.rethink1.Supplier;

public class Product {


    protected int quantity = 0;
    protected String productUID;
    protected Supplier supplier;
    protected double priceCents;
    protected long ean13;
    protected double vatRate;

    public String getProductUID() {
        return productUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void remove() {
        --this.quantity;
    }
}

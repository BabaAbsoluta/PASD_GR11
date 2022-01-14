package com.example.rethink1;

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

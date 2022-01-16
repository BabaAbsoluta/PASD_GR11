package com.example.rethink1.stock_prediction;

import com.example.rethink1.stock_ordering.Supplier;

/**
 * Describes a product object, each product has a quantity, a unique ID, a supplier which supplies the product
 * the price of product (cents), the EAN13 barcode of the product and the vat rate (%) of the product
 */
public class Product {

    protected String name;
    protected int quantity;
    protected String productUID;
    protected Supplier supplier;
    protected double priceCents;
    protected long ean13;
    protected double vatRate;

    public Product(String name, int quantity, String productUID, Supplier supplier, double priceCents, long ean13, double vatRate) {
        this.name = name;
        this.quantity = quantity;
        this.productUID = productUID;
        this.supplier = supplier;
        this.priceCents = priceCents;
        this.ean13 = ean13;
        this.vatRate = vatRate;
    }

    public String getProductUID() {
        return productUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void remove() {
        --this.quantity;
    }

    public void add(){
        ++this.quantity;
    }
}

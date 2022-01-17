package com.example.rethink1.stock_ordering;

import lombok.Getter;

@Getter
public class SupplierProducts {

    protected int id;
    protected String name;
    protected String EAN_13;
    protected double vat_rate;
    protected int price_in_cents;

    public SupplierProducts(int id, String name, String EAN_13, double vat_rate, int price_in_cents) {
        this.id = id;
        this.name = name;
        this.EAN_13 = EAN_13;
        this.vat_rate = vat_rate;
        this.price_in_cents = price_in_cents;
    }

    @Override
    public String toString() {
        return "SupplierProducts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", EAN_13='" + EAN_13 + '\'' +
                ", vat_rate=" + vat_rate +
                ", price_in_cents=" + price_in_cents +
                '}';
    }
}

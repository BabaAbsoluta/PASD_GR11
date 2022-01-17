package com.example.rethink1.stock_prediction;

import com.example.rethink1.stock_ordering.Supplier;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Describes a product object, each product has a quantity, a unique ID, a supplier which supplies the product
 * the price of product (cents), the EAN13 barcode of the product and the vat rate (%) of the product
 */
@Entity
@Getter
@Setter
public class Product implements Serializable {

    @Id
    protected int product_id;
    protected String name;
    protected int nr_of_products;
    @OneToOne
    @JoinColumn(name = "supplier_supplier_uid")
    protected Supplier supplier;
    protected int price_in_cents;
    protected String EAN_13;
    protected double vat_rate;

    public Product() {

    }

    public Supplier getSupplier() {
        return supplier;
    }


    public Product(String name, int quantity, int product_id, Supplier supplier, int priceCents, String ean13, double vatRate) {
        this.name = name;
        this.nr_of_products = quantity;
        this.product_id = product_id;
        this.supplier = supplier;
        this.price_in_cents = priceCents;
        this.EAN_13 = ean13;
        this.vat_rate = vatRate;
    }

    public void remove() {
        --this.nr_of_products;
    }

    public void add(){
        ++this.nr_of_products;
    }


    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", nr_of_products=" + nr_of_products +
                ", supplier=" + supplier +
                ", price_in_cents=" + price_in_cents +
                ", EAN_13='" + EAN_13 + '\'' +
                ", vat_rate=" + vat_rate +
                '}';
    }
}

package com.example.rethink1.stock_prediction;

import com.example.rethink1.stock_ordering.Supplier;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Describes a product object, each product has a quantity, a unique ID, a supplier which supplies the product
 * the price of product (cents), the EAN13 barcode of the product and the vat rate (%) of the product
 */
@Getter
@Setter
@Entity //Assures that we can store/save this object into the database.
public class Product {

    @Id
    protected String productUID;
    protected String name;
    protected int quantity;
    protected Supplier supplier;
    protected double priceCents;
    protected long ean13;
    protected double vatRate;

    /**
     * Empty constructor for the database.
     */
    public Product() {
    }

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

    /**
     * @return String representation of a Product object. Used when printing the database
     */
    @Override
    public String toString() {
        return "Product{" +
                "productUID='" + productUID + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", supplier=" + supplier +
                ", priceCents=" + priceCents +
                ", ean13=" + ean13 +
                ", vatRate=" + vatRate +
                '}';
    }
}

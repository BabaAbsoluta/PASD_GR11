package com.example.rethink1;

import com.example.rethink1.stock_ordering.SupplierAPI;
import com.example.rethink1.stock_prediction.Product;

import java.util.HashMap;

public class Supplier {
    protected int supplierUID;
    protected HashMap<Product, Integer> inventorySupplier;
    protected SupplierAPI supplierAPI;

    public Supplier(int supplierUID) {
        this.supplierUID = supplierUID;
        this.inventorySupplier = new HashMap<>();
        this.supplierAPI = SupplierAPI.getInstance();;
    }

    public void getInventorySupplier(){

    }


}

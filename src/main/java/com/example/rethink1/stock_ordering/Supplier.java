package com.example.rethink1.stock_ordering;

import com.example.rethink1.stock_ordering.SupplierAPI;
import com.example.rethink1.stock_prediction.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Supplier {

    @Id
    protected int supplierUID;
    protected List<Product> inventorySupplier;
    //protected SupplierAPI supplierAPI;

    /**
     * Empty constructor for the database.
     */
    public Supplier() {
    }

    public Supplier(int supplierUID) {
        this.supplierUID = supplierUID;
        this.inventorySupplier = new ArrayList<>();
        //this.supplierAPI = SupplierAPI.getInstance();;
    }

    public void createList(){
        //List = From the supplierAPI getProducts
    }


    @Override
    public String toString() {
        return "Supplier{" +
                "supplierUID=" + supplierUID +
                ", inventorySupplier=" + inventorySupplier +
                '}';
    }
}

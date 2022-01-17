package com.example.rethink1.stock_ordering;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Supplier {

    @Id
    protected int supplierUID;
    @OneToMany
    protected List<SupplierProducts> supplierList;
    transient SupplierAPI supplierAPI;

    /**
     * Empty constructor for the database.
     */
    public Supplier() {
    }

    public Supplier(int supplierUID) {
        this.supplierUID = supplierUID;
        this.supplierList = new ArrayList<>();
        this.supplierAPI = SupplierAPI.getInstance();
        getInventorySupplier();
    }

    public void getInventorySupplier(){
        supplierList = supplierAPI.getSupplierProductsList();
    }


    @Override
    public String toString() {
        return "Supplier{" +
                "supplierUID=" + supplierUID +
                ", supplierList=" + supplierList +
                '}';
    }
}

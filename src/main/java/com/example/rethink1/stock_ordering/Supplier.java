package com.example.rethink1.stock_ordering;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
public class Supplier {

    @Id
    @Getter
    protected int supplierUID;

    @OneToMany
    protected List<SupplierProducts> supplierList;
    protected List<Order> supplierOrderList;
    protected List<Delivery> supplierDeliveryList;
    transient SupplierAPI supplierAPI;

    /**
     * Empty constructor for the database.
     */
    public Supplier() {
    }

    public Supplier(int supplierUID) {
        this.supplierUID = supplierUID;
        this.supplierList = new ArrayList<>();
        this.supplierOrderList = new ArrayList<>();
        this.supplierDeliveryList = new ArrayList<>();
        this.supplierAPI = SupplierAPI.getInstance();
        getInventorySupplier();
        getSupplierOrderList();
        getDeliveryList();
    }

    public void getInventorySupplier(){
        supplierList = supplierAPI.getSupplierProductsList();
    }

    public void getSupplierOrderList(){
        supplierOrderList.clear();
        supplierAPI.getALLOrdersAPI();
        supplierOrderList = supplierAPI.getSupplierOrderList();
    }

    public void getDeliveryList(){
        supplierDeliveryList.clear();
        supplierAPI.viewDeliveryList();
        supplierDeliveryList = supplierAPI.getSupplierDeliveryList();
    }


    @Override
    public String toString() {
        return "Supplier{" +
                "supplierUID=" + supplierUID +
                ", supplierList=" + supplierList +
                ", supplierOrderList=" + supplierOrderList +
                ", supplierDeliveryList=" + supplierDeliveryList +
                '}';
    }
}

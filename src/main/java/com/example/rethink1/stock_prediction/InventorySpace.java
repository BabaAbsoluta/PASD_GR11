package com.example.rethink1.stock_prediction;

import com.example.rethink1.database.DatabaseManager;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class describes an Inventory space for the supermarket
 * Each Inventory object has products and number of products in the inventory.
 */
@Getter
@Setter
@Entity
public class InventorySpace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int inventoryUID;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
    protected int numberProducts;

    /**
     * Empty constructor for the database.
     */
    public InventorySpace(){}


    public InventorySpace(int numberProducts) {
        this.products = new ArrayList<>();
        this.numberProducts = numberProducts;
    }

    public InventorySpace(List<Product> products, int numberProducts) {
        this.products = products;
        this.numberProducts = numberProducts;
    }


    /** Removes a product from the inventory space. Done when a customer buys a product
     * @param product Product being bought by the customer
     */
    public void removeProduct(Product product, DatabaseManager dbm) {
        InventorySpace inventorySpace = dbm.getInfoInventory().get(0);
        dbm.removeInventory(inventorySpace.getInventoryUID());
        // if removing it is valid then
        if (inventorySpace.numberProducts > 0) {
            // reduce the number of products
            --inventorySpace.numberProducts;

            for (Product p : inventorySpace.products) {
                if(p.getProduct_id() == product.getProduct_id()) {
                    // if there are more than one quantity of the product
                    if(p.getNr_of_products() > 1){
                        // minus the quantity
                        p.remove();
                    }
                    else {
                        // else remove the product from the products arraylist
                        inventorySpace.products.remove(p);
                    }
                }
            }
        }
        dbm.addInventory(inventorySpace);
    }

    /**
     * Adds products to the inventory. Done after supply order is recieved from the supplier
     * @param product the product to add to the inventory
     */
    public void addProduct(Product product, DatabaseManager dbm) {
        InventorySpace inventorySpace = dbm.getInfoInventory().get(0);
        dbm.removeInventory(inventorySpace.getInventoryUID());
        for (Product p : inventorySpace.products) {
            // check if product exists in inventory, if it does then increment quantity
            if (p.getProduct_id() == product.getProduct_id()) {
                p.add();
                return;
            }
        }
        // else add product to the inventory
        inventorySpace.products.add(product);
        dbm.addInventory(inventorySpace);
    }

    /**
     * Shows the contents of the inventory
     */
    public void show() {
        System.out.println("InventoryUID: " + this.getInventoryUID());
        for (Product p: this.getProducts()) {
            System.out.println(p.toString());
        }
        System.out.println("Number of products: " + this.getNumberProducts());
        
    }

    public Product findProduct(String name) {
        for (Product p: this.products){
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }

        return null;
    }

    /**
     * @return String representation of the object InventorySpace
     */
    @Override
    public String toString() {
        return "InventorySpace{" +
                "inventoryUID=" + inventoryUID +
                ", products=" + products +
                ", numberProducts=" + numberProducts +
                '}';
    }
}

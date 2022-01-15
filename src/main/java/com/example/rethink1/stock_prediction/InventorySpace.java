package com.example.rethink1.stock_prediction;

import java.util.ArrayList;

/**
 * Class describes an Inventory space for the supermarket
 * Each Inventory object has products and number of products in the inventory.
 */
public class InventorySpace {
    protected ArrayList<Product> products;
    protected int numberProducts;

    public InventorySpace() {
        this.products = new ArrayList<>();
        this.numberProducts = 0;
    }

    /** Removes a product from the inventory space. Done when a customer buys a product
     * @param product Product being bought by the customer
     */
    public void removeProduct(Product product) {
        // if removing it is valid then
        if (numberProducts > 0) {
            // reduce the number of products
            --numberProducts;

            for (Product p : this.products) {
                if(p.getProductUID().equals(product.getProductUID())) {
                    // if there are more than one quantity of the product
                    if(p.getQuantity() > 1){
                        // minus the quantity
                        p.remove();
                    }
                    else {
                        // else remove the product from the products arraylist
                        products.remove(p);
                    }
                }
            }
        }
    }

    /**
     * Adds products to the inventory. Done after supply order is recieved from the supplier
     * @param product the product to add to the inventory
     */
    public void addProduct(Product product) {
        for (Product p : this.products) {
            // check if product exists in inventory, if it does then increment quantity
            if (p.getProductUID().equals(product.getProductUID())) {
                p.add();
                return;
            }
        }
        // else add product to the inventory
        products.add(product);
    }

    /**
     * Shows the contents of the inventory
     */
    public void show() {
        // TODO: display inventory database in a nice manner
    }

    public Product findProduct(String name) {
        for (Product p: this.products){
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }

        return null;
    }

}

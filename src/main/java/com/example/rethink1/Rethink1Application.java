package com.example.rethink1;

import com.example.rethink1.database.DatabaseManager;
import com.example.rethink1.stock_ordering.VirtualBasket;
import com.example.rethink1.stock_prediction.InventorySpace;
import com.example.rethink1.stock_prediction.Manager;
import com.example.rethink1.stock_prediction.Product;
import com.example.rethink1.stock_prediction.ShoppingPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Rethink1Application implements CommandLineRunner {

    @Autowired
    private ConfigurableApplicationContext context;
    private static SpringApplication springApplication;
    private DatabaseManager dbm;

    public static void main(String[] args) {
        springApplication.run(Rethink1Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        dbm = new DatabaseManager("RethinkODB");
        InventorySpace inventorySpace = dbm.getInfoInventory().get(0);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter: \n" +
                "(1) To check inventory\n" +
                "(2) To add customer transaction\n" +
                "(3) To check incoming stock orders\n" +
                "(4) Exit\n");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                inventorySpace.show(dbm);
                System.out.println("Works");
                break;
            case 2:
                addVirtualBasket(inventorySpace);
                break;
            case 3:
                checkStockOrder();
                break;
            case 4:
                System.exit(springApplication.exit(context));
                break;
            default:
                System.out.println("Enter a valid choice");
                choice = sc.nextInt();
        }
    }


    /** Adds a customers virtual basket to their shopping portfolio and updates the database
     * TODO: Move this to the event listener (not in the correct place)
     * @param inventorySpace the inventory space of the store
     */
    public void addVirtualBasket(InventorySpace inventorySpace) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer UID");
        String uid = sc.nextLine();

        System.out.println("Enter number of products");
        int num = sc.nextInt();
        System.out.println("Enter payment method");
        String payment = sc.nextLine();

        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < num; ++i) {
            System.out.println("Enter product name");
            String name = sc.nextLine();
            System.out.println("Enter product quantity");
            int quantity = sc.nextInt();
            Product p = inventorySpace.findProduct(name);


            if (p == null) {
                System.out.println("Not a valid product");
            } else {
                p.setQuantity(quantity + p.getQuantity());
                products.add(p);
            }
        }
        LocalDate date = LocalDate.now();
        VirtualBasket basket = new VirtualBasket(products, payment, date);
        List<ShoppingPortfolio> shoppingPortfolioList = dbm.getAllShoppingPortfolios();

        for (ShoppingPortfolio s: shoppingPortfolioList) {
            if (s.getCustomerUID().equals(uid)) {
                s.addPurchase(basket);
                dbm.removeShoppingPortfolio(uid);
                dbm.addShoppingPortfolio(s);
            }
        }

    }

    public void checkStockOrder() {
        // TODO: implementation
    }

}
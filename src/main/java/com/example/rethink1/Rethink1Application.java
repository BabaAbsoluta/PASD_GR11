package com.example.rethink1;

import com.example.rethink1.database.DatabaseManager;
import com.example.rethink1.events.Event;
import com.example.rethink1.stock_ordering.Supplier;
import com.example.rethink1.stock_ordering.VirtualBasket;
import com.example.rethink1.stock_prediction.InventorySpace;
import com.example.rethink1.stock_prediction.Product;
import com.example.rethink1.stock_prediction.ShoppingPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Rethink1Application implements CommandLineRunner {

    @Autowired
    private ConfigurableApplicationContext context;
    private static SpringApplication springApplication;
    private DatabaseManager dbm;
    private Event event;
    public static void main(String[] args) {
        springApplication.run(Rethink1Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        dbm = DatabaseManager.getInstance();
//
//         Product p1 = new Product("Butter",10,3,2,239, "30176240107",
//                 0.09);
//         Product p2 = new Product("Shower Gel",10,8,2,959, "668205008014", 0.21);
//
//        List<Product> products = new ArrayList<>();
//        products.add(p1);
//        products.add(p2);
//
//        InventorySpace inventory = new InventorySpace(products, 2);
//        dbm.addInventory(inventory);

        InventorySpace inventorySpace = dbm.getInfoInventory().get(0);

        // kind of like the main screen
        Scanner sc = new Scanner(System.in);
        String message = "Enter: \n" +
                "(1) To check inventory\n" +
                "(2) To add customer transaction\n" +
                "(3) To check incoming stock orders\n" +
                "(4) Exit\n";
        System.out.println(message);

        int choice = sc.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    inventorySpace.show();
                    System.out.println(message);
                    break;
                case 2:
                    addVirtualBasket(inventorySpace);
                    System.out.println(message);
                    break;
                case 3:
                    checkStockOrder();
                    System.out.println(message);
                    break;
                case 4:
                    dbm.closeDataBase();
                    System.exit(springApplication.exit(context));
                    break;
                default:
                    System.out.println(message);
            }
            choice = sc.nextInt();
        }
    }

    /** Adds a customers virtual basket to their shopping portfolio and updates the database
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
                p.setNr_of_products(quantity + p.getNr_of_products());
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

        //event triggered to update stock prediction and process payment
        basket.getEventPublisher().publishEvent("newPurchaseEvent");

    }

    public void checkStockOrder() {
        // TODO: implementation
    }

}
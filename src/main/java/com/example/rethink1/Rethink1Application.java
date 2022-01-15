package com.example.rethink1;

import com.example.rethink1.stock_prediction.InventorySpace;
import com.example.rethink1.stock_prediction.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class Rethink1Application implements CommandLineRunner {

    @Autowired
    private ConfigurableApplicationContext context;
    private static SpringApplication springApplication;

    public static void main(String[] args) {
        springApplication.run(Rethink1Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        InventorySpace inventorySpace = new InventorySpace(); // #TODO: get from database
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter: \n" +
                "(1) To check inventory\n" +
                "(2) To add customer transaction\n" +
                "(3) To check incoming stock orders\n" +
                "(4) Exit\n");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                inventorySpace.show();
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

    public void addVirtualBasket(InventorySpace inventorySpace) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer UID");
        String uid = sc.nextLine();
        // TODO: get corresponding purchase history for product and add a virtual basket
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
                products.add(p);
            }
        }
        LocalDate date = LocalDate.now();
        VirtualBasket basket = new VirtualBasket(products, payment, date);

    }

    public void checkStockOrder() {
        // TODO: implementation
    }

}
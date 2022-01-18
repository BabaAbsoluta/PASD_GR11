package com.example.rethink1;

import com.example.rethink1.database.DatabaseManager;
import com.example.rethink1.events.Event;
import com.example.rethink1.events.EventPublisher;
import com.example.rethink1.payment_system.Customer;
import com.example.rethink1.stock_ordering.SupplierAPI;
import com.example.rethink1.stock_ordering.VirtualBasket;
import com.example.rethink1.stock_prediction.InventorySpace;
import com.example.rethink1.stock_prediction.Product;
import com.example.rethink1.stock_prediction.ShoppingPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    private Event event;
    private SupplierAPI supplierAPI;

    public static void main(String[] args) {
        springApplication.run(Rethink1Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        dbm = DatabaseManager.getInstance();
        supplierAPI = SupplierAPI.getInstance();

        Customer c1 = new Customer("1111","Miguel","megusta",1400,true);
        Customer c2 = new Customer("1112","Rodrigo","megustaMiguel",1900,true);
        Customer c3 = new Customer("1113","Fernando","varoom",1700,true);
        Customer c4 = new Customer("1114","Windy","megusta",1500,true);
        Customer c5 = new Customer("1115","Esteban Julio Ricardo Montoya de la Rosa Ramirez","password",1400,true);
        Customer c6 = new Customer("1116","Bob","chicken",999,false);
        Customer c7 = new Customer("1117","Lola","labradoodle",1800,true);
        Customer c8 = new Customer("1118","Rochelle","VvKyyu2;rrqUB&b",1500,true);
        Customer c9 = new Customer("1119","Pink","floyd",1300,true);


        ShoppingPortfolio s1 = new ShoppingPortfolio("1111");
        ShoppingPortfolio s2 = new ShoppingPortfolio("1112");
        ShoppingPortfolio s3 = new ShoppingPortfolio("1113");
        ShoppingPortfolio s4 = new ShoppingPortfolio("1114");
        ShoppingPortfolio s5 = new ShoppingPortfolio("1115");
        ShoppingPortfolio s6 = new ShoppingPortfolio("1116");
        ShoppingPortfolio s7 = new ShoppingPortfolio("1117");
        ShoppingPortfolio s8 = new ShoppingPortfolio("1118");
        ShoppingPortfolio s9 = new ShoppingPortfolio("1119");

//         Product p1 = new Product("Butter",10,3,2,239, "30176240107", 0.09);
//         Product p2 = new Product("Shower Gel",10,8,2,959, "668205008014", 0.21);
//           Product p3 = new Product("Jam",10,12,2,199, "49186164127", 0.09);
//           Product p4 = new Product("Peanut Butter",10,13,2,269, "32146124127", 0.09);
//           Product p5 = new Product("Skin Cream",10,14,2,549, "868125360514", 0.21);
//           Product p6 = new Product("Handwash Soap",10,15,2,129, "868403158014", 0.21);
//           Product p7 = new Product("Tissues",10,16,2,70, "33183284199", 0.21);
//           Product p8 = new Product("Toothpaste",10,17,2,175, "878325660515", 0.21);
//           Product p9 = new Product("Deoderant",10,18,2,289, "868405212713", 0.21);
//           Product p10 = new Product("Coffee Powder",10,19,2,120, "862183365512", 0.09);
//           Product p11 = new Product("Tea",10,20,2,105, "860412148014", 0.09);
//           Product p12 = new Product("Milk",10,21,2,80, "858225370911", 0.09);
//           Product p13 = new Product("Cheese",10,22,2,70, "839425508313", 0.09);
//           Product p14 = new Product("Eggs (x10)",10,4,2,349, "32186244127", 0.09);
//           Product p15 = new Product("Ketchup",10,5,2,179, "868405000014", 0.09);
//           Product p16 = new Product("Bread",10,6,2,107, "869465002314", 0.09);
//           Product p17 = new Product("Basmati rice",10,7,2,439, "82186947127", 0.09);
//           Product p18 = new Product("Fruit Juice",10,9,2,245, "32146284120", 0.09);
//           Product p19 = new Product("Sunflower Oil",10,10,2,289, "85186937107", 0.09);
//           Product p20 = new Product("Crackers",10,11,2,78, "37182204124", 0.09);
//
//        List<Product> products = new ArrayList<>();
//        products.add(p1);
//        products.add(p2);
//        products.add(p3);
//        products.add(p4);
//        products.add(p5);
//        products.add(p6);
//        products.add(p7);
//        products.add(p8);
//        products.add(p9);
//        products.add(p10);
//        products.add(p11);
//        products.add(p12);
//        products.add(p13);
//        products.add(p14);
//        products.add(p15);
//        products.add(p16);
//        products.add(p17);
//        products.add(p18);
//        products.add(p19);
//        products.add(p20);
//
//        InventorySpace inventory = new InventorySpace(products, products.size());
//        dbm.addInventory(inventory);

        InventorySpace inventorySpace = dbm.getInfoInventory().get(0);

        // kind of like the main screen
        Scanner sc = new Scanner(System.in);
        String message = "Enter: \n" +
                "(1) To check inventory\n" +
                "(2) To add customer transaction\n" +
                "(3) To check incoming stock orders\n" +
                "(4) To check a customer's purchase history\n" +
                "(5) To see current prediction\n" +
                "(6) Exit\n";
        System.out.println(message);

        int choice = sc.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    // to show current inventory
                    inventorySpace.show();
                    System.out.println(message);
                    break;
                case 2:
                    // to mimic a customer buying products
                    addVirtualBasket();
                    System.out.println(message);
                    break;
                case 3:
                    // for manager to see what the current stock orders are
                    checkStockOrder();
                    System.out.println(message);
                    break;
                case 4:
                    // for prediction algorithm, to check a customer's history
                    checkCustomerHistory();
                    break;
                case 5:
                    // for manager, mimics the approval of a prediction algorithm
                    EventPublisher eventPublisher;
                    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
                    eventPublisher = (EventPublisher) context.getBean("eventPublisher");
                    eventPublisher.publishEvent("newPredictionEvent");
                    break;
                case 6:
                    // exit the application
                    dbm.closeDataBase();
                    System.exit(springApplication.exit(this.context));
                    break;
                default:
                    // for illegal input
                    System.out.println(message);
            }
            choice = sc.nextInt();
        }
    }

    /** Adds a customers virtual basket to their shopping portfolio and updates the database
     */
    public void addVirtualBasket() {
        EventPublisher eventPublisher;
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        eventPublisher = (EventPublisher) context.getBean("eventPublisher");
        eventPublisher.publishEvent("newPurchaseEvent");
    }

    public void checkStockOrder() {
        supplierAPI.viewDeliveryList();
    }

    /**
     * Checking a customers purchase history aka their previous virtual basket objects
     */
    public void checkCustomerHistory() {
        System.out.println("Enter customer UID");
        Scanner sc = new Scanner(System.in);
        String uid = sc.nextLine();
        List<ShoppingPortfolio> shoppingPortfolioList = dbm.getAllShoppingPortfolios();

        for (ShoppingPortfolio s : shoppingPortfolioList) {
            if (s.getCustomerUID().equals(uid)) {
                System.out.println("Works");
                List<VirtualBasket> virtualBaskets = s.getPurchaseHistory();
                for (VirtualBasket b : virtualBaskets) {
                    System.out.println(b);
                }
            }
        }
    }

}
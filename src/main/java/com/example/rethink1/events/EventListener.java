package com.example.rethink1.events;

import com.example.rethink1.database.DatabaseManager;
import com.example.rethink1.stock_ordering.OrderLine;
import com.example.rethink1.stock_ordering.VirtualBasket;
import com.example.rethink1.stock_prediction.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationListener;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class EventListener implements ApplicationListener<Event> {

    protected StockPrediction stockforecast;
    protected OrderLine orderline;
    protected InventorySpace inventorySpace;
    protected VirtualBasket virtualBasket;

    public void onApplicationEvent(Event event) {

        // what to do when event is triggered
        if (event.getMessage().equals("newPredictionEvent")) {

            // create an orderline
            this.orderline = stockforecast.predict();

            // approve it from manager
            // get manager from database
            Manager manager = new Manager();
            boolean approval = manager.approve(orderline);

            if (approval) {
                stockforecast.sendPrediction();
            }

        }

        // mimicks the purchase of a new event
        if (event.getMessage().equals("newPurchaseEvent")) {
            DatabaseManager dbm = DatabaseManager.getInstance();
            Scanner sc = new Scanner(System.in);

            // get details of the purchase
            System.out.println("Enter customer UID");
            int uid = Integer.parseInt(sc.nextLine());
            System.out.println("Enter number of products");
            int num = Integer.parseInt(sc.nextLine());
            System.out.println("Enter payment method");
            String payment = sc.nextLine();


            ArrayList<Product> products = new ArrayList<>();
            int i = 0;
            while (i < num) {
                System.out.println("Enter product name");
                String name = sc.nextLine();
                Product p = inventorySpace.findProduct(name);

                // if product does not exist in inventory...
                if (p == null) {
                    System.out.println("Not a valid product");
                } else {    // otherwise if it does
                    System.out.println("Enter product quantity");
                    int quantity = Integer.parseInt(sc.nextLine());
                    // remove it from the inventory
                    inventorySpace.removeProduct(p, quantity);
                    // add it to the virtual basket's products
                    products.add(p);
                    i++;
                }
            }

            LocalDate date = LocalDate.now();

            // create a new virtual basket, for purchase history tracking
            VirtualBasket basket = new VirtualBasket(products, payment, date, uid);

            // get shopping portfolios
            List<ShoppingPortfolio> shoppingPortfolioList = dbm.getAllShoppingPortfolios();
            boolean found = false;

            for (ShoppingPortfolio s: shoppingPortfolioList) {
                // check if customer has a shopping portfolio
                if (s.getCustomerUID().equals(String.valueOf(uid))) {
                    s.addPurchase(basket);
                    dbm.removeShoppingPortfolio(String.valueOf(uid));
                    dbm.addShoppingPortfolio(s);
                    found = true;
                }
            }

            if (!found) {
                // otherwise if customer does not have a shopping portfolio add to odb
                ShoppingPortfolio shoppingPortfolio = new ShoppingPortfolio(String.valueOf(uid));
                shoppingPortfolio.addPurchase(basket);
                dbm.addShoppingPortfolio(shoppingPortfolio);
            }

            // create a new stock forecast based on the new change in inventory
            this.stockforecast = new StockPrediction();
            stockforecast.predict();
        }

        if (event.getMessage().equals("newStockEvent")) {
            // update inventory object

            // create a new stock forecast
            this.stockforecast = new StockPrediction();
            stockforecast.predict();
        }
    }
}

package com.example.rethink1.stock_prediction;

import com.example.rethink1.database.DatabaseManager;
import com.example.rethink1.events.EventPublisher;
import com.example.rethink1.stock_ordering.OrderLine;
import com.example.rethink1.stock_ordering.VirtualBasket;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class StockPrediction {
    protected static EventPublisher eventPublisher;
    protected OrderLine orderline;
    public StockPrediction() {
        init();
    }

    public void init() {
        // adding the event
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.eventPublisher = (EventPublisher) context.getBean("eventPublisher");
        predict();

    }

    public OrderLine predict() {
        // not really a prediction algorithm but for placement purposes
        this.orderline = new OrderLine();

        DatabaseManager dbm = DatabaseManager.getInstance();
        List<ShoppingPortfolio> shoppingPortfolios = dbm.getAllShoppingPortfolios();
        for (ShoppingPortfolio shoppingPortfolio: shoppingPortfolios) {
            List<VirtualBasket> baskets = shoppingPortfolio.getPurchaseHistory();
            for (VirtualBasket basket: baskets) {
                List<Product> products = basket.getProducts();
                for (Product product: products) {
                    if (product.getNr_of_products() <= 2) {
                        orderline.setProduct_id(product.getProduct_id());
                        orderline.setNr_of_products(10);
                        orderline.setOrder_id(1);
                        return orderline;

                    }
                }
            }

        }
        return orderline;
    }

    public void sendPrediction() {
            this.eventPublisher.publishEvent("newPredictionEvent");
    }



}

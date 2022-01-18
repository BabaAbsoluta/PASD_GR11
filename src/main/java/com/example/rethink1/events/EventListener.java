package com.example.rethink1.events;

import com.example.rethink1.database.DatabaseManager;
import com.example.rethink1.stock_ordering.OrderLine;
import com.example.rethink1.stock_ordering.VirtualBasket;
import com.example.rethink1.stock_prediction.InventorySpace;
import com.example.rethink1.stock_prediction.Manager;
import com.example.rethink1.stock_prediction.ShoppingPortfolio;
import com.example.rethink1.stock_prediction.StockPrediction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationListener;

import javax.swing.*;
import java.util.List;

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

        if (event.getMessage().equals("newPurchaseEvent")) {
            // create a new stock forecast
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

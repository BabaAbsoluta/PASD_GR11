package com.example.rethink1.events;

import com.example.rethink1.stock_ordering.OrderLine;
import com.example.rethink1.stock_prediction.Manager;
import com.example.rethink1.stock_prediction.StockPrediction;
import org.springframework.context.ApplicationListener;

import javax.swing.*;

public class EventListener implements ApplicationListener<Event> {

    private StockPrediction stockforecast;
    private OrderLine orderline;

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
            else {
                System.out.println("Not approved");
            }

        }

        if (event.getMessage().equals("newPurchaseEvent")) {
            // update inventory object

            // update customers shopping portfolio

            // create a new stock forecast
            this.stockforecast = new StockPrediction();
            stockforecast.predict();

            // process payment
        }

        if (event.getMessage().equals("newStockEvent")) {
            // update inventory object

            // create a new stock forecast
            this.stockforecast = new StockPrediction();
            stockforecast.predict();
        }
    }
}

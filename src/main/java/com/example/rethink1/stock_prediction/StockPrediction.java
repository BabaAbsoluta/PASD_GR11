package com.example.rethink1.stock_prediction;

import com.example.rethink1.events.EventPublisher;
import com.example.rethink1.stock_ordering.OrderLine;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class StockPrediction {
    protected ArrayList<Product> products;
    protected static EventPublisher eventPublisher;
    protected OrderLine orderline;
    public StockPrediction() {
        init();
    }

    public void init() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
         this.eventPublisher = (EventPublisher) context.getBean("eventPublisher");
         predict();

    }

    public OrderLine predict() {
        // TODO: prediction algorithm
         this.orderline = new OrderLine();
        return this.orderline;
    }

    public void sendPrediction() {
        boolean approved = true;

        if (approved) {
            this.eventPublisher.publishEvent("newPredictionEvent");
        }
    }

}

package com.example.rethink1.stock_ordering;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Order implements Serializable {

    public int order_id;
    public int buyer;
    public boolean is_processed;

    public Order(int order_id) {
        this.order_id = order_id;
    }

    public Order(int order_id, int buyer, boolean is_processed) {
        this.order_id = order_id;
        this.buyer = buyer;
        this.is_processed = is_processed;
    }


    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", buyer=" + buyer +
                ", is_processed=" + is_processed +
                '}';
    }
}

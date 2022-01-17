package com.example.rethink1.stock_ordering;

import lombok.Getter;



@Getter
public class Delivery {

    protected int delivery_id;
    protected String date_time;
    protected int order_id;

    public Delivery(int delivery_id, String date_time, int order_id) {
        this.delivery_id = delivery_id;
        this.date_time = date_time;
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "delivery_id=" + delivery_id +
                ", date_time='" + date_time + '\'' +
                ", order_id=" + order_id +
                '}';
    }
}

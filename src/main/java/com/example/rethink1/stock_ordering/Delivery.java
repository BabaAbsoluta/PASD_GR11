package com.example.rethink1.stock_ordering;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Entity
@Setter
public class Delivery {
    @Id
    protected int delivery_id;
    protected String date_time;
    protected int order_id;

    public Delivery(int delivery_id, String date_time, int order_id) {
        this.delivery_id = delivery_id;
        this.date_time = date_time;
        this.order_id = order_id;
    }

    public Delivery() {

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

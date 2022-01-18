package com.example.rethink1.stock_ordering;

import lombok.Getter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Entity
public class Order implements Serializable {

    public int id;
    public int buyer;
    public boolean is_processed;

    public Order(int order_id) {
        this.id = order_id;
    }

    public Order(int order_id, int buyer, boolean is_processed) {
        this.id = order_id;
        this.buyer = buyer;
        this.is_processed = is_processed;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }

}

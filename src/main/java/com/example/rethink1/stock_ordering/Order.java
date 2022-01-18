package com.example.rethink1.stock_ordering;

import lombok.Getter;

import javax.jdo.annotations.Index;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Entity
public class Order implements Serializable {

    @Id
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

    public Order() {

    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }

}

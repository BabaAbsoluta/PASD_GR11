package com.example.rethink1.stock_ordering;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order implements Serializable {

    public int id;

    public Order(int order_id) {
        this.id = order_id;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }

}

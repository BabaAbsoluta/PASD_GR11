package com.example.rethink1.stock_ordering;

import lombok.Getter;

@Getter
public class OrderLine {

    //public int orderLineID;
    public int product_id;
    public int order_id;
    public int nr_of_products;

    public OrderLine() {
    }

    public OrderLine(int product_id, int order_id, int nr_of_products) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.nr_of_products = nr_of_products;
        //this.orderLineID = orderLineID;

    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "product_id=" + product_id +
                ", order_id=" + order_id +
                ", nr_of_products=" + nr_of_products +
                '}';
    }
}

package com.example.rethink1.stock_ordering;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderLine {

    public int orderLine_id;
    public int product_id;
    @Setter
    public int order_id;
    public int nr_of_products;

    public OrderLine() {
    }

    public OrderLine(int product_id, int order_id, int nr_of_products) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.nr_of_products = nr_of_products;

    }
    public OrderLine(int product_id, int order_id, int nr_of_products, int orderLine_id) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.nr_of_products = nr_of_products;
        this.orderLine_id = orderLine_id;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLine_id=" + orderLine_id +
                ", product_id=" + product_id +
                ", order_id=" + order_id +
                ", nr_of_products=" + nr_of_products +
                '}';
    }
}

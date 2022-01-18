package com.example.rethink1.stock_ordering;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
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

    public void change(){
        Scanner sc = new Scanner(System.in);
        String message = "Enter: \n" +
                "(1) To change product_id\n" +
                "(2) To change number_of_products\n" +
                "(3) Exit\n";
        System.out.println(message);

        int choice = Integer.parseInt(sc.nextLine());
        while (true) {
            switch (choice) {
                case 1:
                    System.out.println("Enter new product id");
                    this.product_id = Integer.parseInt(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Enter new number_of_products");
                    this.nr_of_products = Integer.parseInt(sc.nextLine());
                    break;
                case 3:
                    return;
                default:
                    System.out.println(message);
                    choice = Integer.parseInt(sc.nextLine());
            }
        }
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

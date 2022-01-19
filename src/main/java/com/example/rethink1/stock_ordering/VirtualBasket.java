package com.example.rethink1.stock_ordering;

import com.example.rethink1.events.Event;
import com.example.rethink1.events.EventPublisher;
import com.example.rethink1.stock_prediction.Product;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jdo.annotations.NotPersistent;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Entity

public class VirtualBasket implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private int customerID;

    @ManyToMany(cascade = CascadeType.ALL)
    protected ArrayList<Product> products;
    protected String paymentMethod;
    protected LocalDate date;

    public VirtualBasket(ArrayList<Product> products, String paymentMethod, LocalDate date, int customerID) {

        this.products = products;
        this.customerID = customerID;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public VirtualBasket() {

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }


    @Override
    public String toString() {
        return "VirtualBasket{" +
                "customerID=" + customerID +
                ", products=" + products +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", date=" + date +
                '}';
    }
}

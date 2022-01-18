package com.example.rethink1.stock_ordering;

import com.example.rethink1.events.Event;
import com.example.rethink1.events.EventPublisher;
import com.example.rethink1.stock_prediction.Product;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jdo.annotations.NotPersistent;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Entity
public class VirtualBasket {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    protected ArrayList<Product> products;
    protected String paymentMethod;
    protected LocalDate date;
    @NotPersistent
    protected EventPublisher eventPublisher;

    public VirtualBasket(ArrayList<Product> products, String paymentMethod, LocalDate date) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        this.eventPublisher = (EventPublisher) context.getBean("eventPublisher");
        this.products = products;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }


}

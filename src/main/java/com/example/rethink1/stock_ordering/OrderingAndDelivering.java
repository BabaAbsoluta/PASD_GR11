package com.example.rethink1.stock_ordering;

import com.example.rethink1.database.DatabaseManager;
import com.example.rethink1.events.EventPublisher;
import org.json.JSONException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderingAndDelivering {

    private Supplier supplier;
    private List<Delivery> deliveryListSystem;
    private static EventPublisher eventPublisher;
    private static OrderingAndDelivering instance;

    public static OrderingAndDelivering getInstance(){
        if (instance == null) {
            instance = new OrderingAndDelivering();
        }
        return instance;
    }

    private OrderingAndDelivering() {
        this.supplier = new Supplier(1);
        this.deliveryListSystem = new ArrayList<>();
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.eventPublisher = (EventPublisher) context.getBean("eventPublisher");

    }

    public void receiveOrderLine(OrderLine orderline) throws JSONException, IOException, InterruptedException {
        //1. Creates a new order
        Order order = new Order(1);
        SupplierAPI supplierAPI = supplier.getSupplierAPIInstance();
        order = supplierAPI.createOrderAPI(order);

        //2. Set given order_is to the orderLine;
        orderline.setOrder_id(order.getOrder_id());

        //3. Add orderLine to SupplierAPI (orderLine_id is not necessary, gets a new id from the API)
        supplierAPI.addOrderLineAPI(orderline);

        //4. Sends order
        supplierAPI.sendOrderAPI(order);

        //5. Check if order is in the list of deliveries
        deliveryListSystem = supplier.fetchDeliveries();
        for (int i = 0; i < deliveryListSystem.size(); i++) {
            Delivery delivery = deliveryListSystem.get(i);
            if (delivery.getOrder_id() == order.getOrder_id() ){
                // order is delivered, send orderLine to the inventory
                eventPublisher.publishEvent("newStockEvent");
            }
        }
    }


}

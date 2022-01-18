package com.example.rethink1.payment_system;

public class Till {

    protected String basketID;
    protected boolean paymentMethod;

    public  Till (String basketID , boolean paymentMethod) {
        this.basketID=basketID;
        this.paymentMethod=paymentMethod;

    }
}
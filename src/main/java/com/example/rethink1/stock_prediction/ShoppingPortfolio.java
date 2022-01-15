package com.example.rethink1.stock_prediction;

import com.example.rethink1.VirtualBasket;

import java.util.ArrayList;

public class ShoppingPortfolio {
    protected String customerUID;
    protected ArrayList<VirtualBasket> purchaseHistory = new ArrayList<>();
    protected String paymentMethod;

}

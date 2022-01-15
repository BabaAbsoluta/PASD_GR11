package com.example.rethink1.stock_prediction;

import com.example.rethink1.VirtualBasket;

import java.util.ArrayList;

public class ShoppingPortfolio {
    protected String customerUID;
    protected ArrayList<VirtualBasket> purchaseHistory;

    public ShoppingPortfolio(String customerUID) {
        this.customerUID = customerUID;
        this.purchaseHistory = new ArrayList<>();
    }

    public void addPurchase(VirtualBasket basket)  {
        purchaseHistory.add(basket);
    }

}

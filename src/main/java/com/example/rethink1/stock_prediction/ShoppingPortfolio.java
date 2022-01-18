package com.example.rethink1.stock_prediction;

import com.example.rethink1.stock_ordering.VirtualBasket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ShoppingPortfolio {

    @Id
    protected String customerUID;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<VirtualBasket> purchaseHistory;

    /**
     * Empty constructor for the database.
     */
    public ShoppingPortfolio() {
    }

    public ShoppingPortfolio(String customerUID) {
        this.customerUID = customerUID;
        this.purchaseHistory = new ArrayList<>();
    }

    public void addPurchase(VirtualBasket basket)  {
        purchaseHistory.add(basket);
    }

    @Override
    public String toString() {
        return "ShoppingPortfolio{" +
                "customerUID='" + customerUID + '\'' +
                ", purchaseHistory=" + purchaseHistory +
                '}';
    }
}

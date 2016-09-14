package com.dev.illiaKa;

/**
 * Created by sonicmaster on 07.09.16.
 * Product class
 */
public class Product {

    String type;
    String price;
    String amount;

    public Product(String type, String price, String amount) {
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

}

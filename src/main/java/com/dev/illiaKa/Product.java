package com.dev.illiaKa;

/**
 * Created by sonicmaster on 07.09.16.
 */
public class Product {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product(String type, String price, String amount) {
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    public String getPrice() {
        return price;

    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String type;
    String price;
    String amount;

}

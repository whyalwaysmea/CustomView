package com.whyalwaysmea.myview.shoppingcar.bean;

import java.io.Serializable;

/**
 * Created by HelloWorld on 2017/2/23.
 */

public class Dishes implements Serializable {
    private String name;
    private int price;

    public Dishes(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

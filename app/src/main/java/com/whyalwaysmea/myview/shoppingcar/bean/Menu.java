package com.whyalwaysmea.myview.shoppingcar.bean;

import java.io.Serializable;

/**
 * Created by HelloWorld on 2017/2/24.
 */

public class Menu implements Serializable {
    public String name;
    public int num;

    public Menu(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

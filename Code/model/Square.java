package model;

import java.io.Serializable;

public abstract class Square implements Serializable {
    protected String name;
    private int price;

    public Square(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public abstract void landOn(Player player);
}
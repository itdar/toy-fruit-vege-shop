package com.preworkout.demo1.fruitshop.dto;

import com.preworkout.demo1.fruitshop.domain.Fruit;

public class FruitRequest {
    private String name;
    private int price;

    private FruitRequest(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static FruitRequest of(String name, int price) {
        return new FruitRequest(name, price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Fruit toFruit() {
        return Fruit.of(name, price);
    }
}

package com.preworkout.demo1.vegeshop.dto;

import com.preworkout.demo1.vegeshop.domain.Vegetable;

public class VegetableRequest {
    private String name;
    private int price;

    private VegetableRequest(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static VegetableRequest of(String name, int price) {
        return new VegetableRequest(name, price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Vegetable toVegetable() {
        return Vegetable.of(name, price);
    }
}

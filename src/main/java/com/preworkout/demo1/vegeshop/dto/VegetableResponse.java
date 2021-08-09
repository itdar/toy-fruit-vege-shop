package com.preworkout.demo1.vegeshop.dto;

import com.preworkout.demo1.vegeshop.domain.Vegetable;

public class VegetableResponse {
    private Long id;
    private String name;
    private int price;

    protected VegetableResponse() {
    }

    private VegetableResponse(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static VegetableResponse of(Vegetable vegetable) {
        return new VegetableResponse(vegetable.getId(), vegetable.getName(), vegetable.getPrice());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}

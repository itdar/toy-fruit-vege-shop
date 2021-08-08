package com.preworkout.demo1.fruitshop.dto;

import com.preworkout.demo1.fruitshop.domain.Fruit;

public class FruitResponse {
    private Long id;
    private String name;
    private int price;

    protected FruitResponse() { }

    private FruitResponse(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static FruitResponse of(Fruit fruit) {
        return new FruitResponse(fruit.getId(), fruit.name(), fruit.price());
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

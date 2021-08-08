package com.preworkout.demo1.fruitshop.domain;

import javax.persistence.Embeddable;

@Embeddable
public class FruitPrice {
    private int price;

    public FruitPrice(int price) {
        validateFruitPrice(price);

        this.price = price;
    }

    protected FruitPrice() { }

    private void validateFruitPrice(int price) {
        if (price < 0 || price > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("유효하지 않은 과일 가격");
        }
    }

    public int get() {
        return this.price;
    }
}

package com.preworkout.demo1.vegeshop.domain;

import javax.persistence.Embeddable;

@Embeddable
public class VegePrice {

    private int price;

    public VegePrice(int price) {
        validateVegePrice(price);

        this.price = price;
    }

    protected VegePrice() {
    }

    private void validateVegePrice(int price) {
        if (price < 0 || price > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("유효하지 않은 채소 가격");
        }
    }

    public int get() {
        return this.price;
    }
}

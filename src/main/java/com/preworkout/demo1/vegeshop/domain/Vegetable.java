package com.preworkout.demo1.vegeshop.domain;

import javax.persistence.*;

@Entity
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private VegePrice price;

    protected Vegetable() {
    }

    private Vegetable(String name, int price) {
        this.name = name;
        this.price = new VegePrice(price);
    }

    public static Vegetable of(String name, int price) {
        return new Vegetable(name, price);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price.get();
    }

}

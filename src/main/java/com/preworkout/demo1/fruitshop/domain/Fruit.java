package com.preworkout.demo1.fruitshop.domain;

import javax.persistence.*;

@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Embedded
    private FruitPrice price;

    protected Fruit() { }

    private Fruit(String name, int price) {
        this.name = name;
        this.price = new FruitPrice(price);
    }

    public static Fruit of(String name, int price) {
        return new Fruit(name, price);
    }

    public String name() {
        return this.name;
    }

    public int price() {
        return price.get();
    }

    public Long getId() {
        return this.id;
    }
}

package com.preworkout.demo1.fruitshop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    Optional<Fruit> findByName(String name);
}

package com.preworkout.demo1.fruitshop.domain;

import com.preworkout.demo1.fruitshop.dto.FruitResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    Fruit findByName(String name);
}

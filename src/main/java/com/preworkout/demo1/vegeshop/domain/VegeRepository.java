package com.preworkout.demo1.vegeshop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VegeRepository extends JpaRepository<Vegetable, Long> {

    Vegetable findByName(String name);

}

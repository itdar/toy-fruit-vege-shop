package com.preworkout.demo1.vegeshop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VegeRepository extends JpaRepository<Vegetable, Long> {

    Optional<Vegetable> findByName(String name);

}

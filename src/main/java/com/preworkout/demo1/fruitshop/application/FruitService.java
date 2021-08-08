package com.preworkout.demo1.fruitshop.application;

import com.preworkout.demo1.fruitshop.domain.Fruit;
import com.preworkout.demo1.fruitshop.domain.FruitRepository;
import com.preworkout.demo1.fruitshop.dto.FruitRequest;
import com.preworkout.demo1.fruitshop.dto.FruitResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public FruitResponse save(FruitRequest fruitRequest) {
        return FruitResponse.of(fruitRepository.save(fruitRequest.toFruit()));
    }

    @Transactional(readOnly = true)
    public List<FruitResponse> findAllFruit() {
        List<Fruit> fruitList = fruitRepository.findAll();
        return fruitList.stream()
                .map(FruitResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FruitResponse findFruitByName(String name) {
        return FruitResponse.of(fruitRepository.findByName(name));
    }
}

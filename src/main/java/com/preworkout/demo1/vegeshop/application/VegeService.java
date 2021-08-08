package com.preworkout.demo1.vegeshop.application;

import com.preworkout.demo1.vegeshop.domain.VegeRepository;
import com.preworkout.demo1.vegeshop.domain.Vegetable;
import com.preworkout.demo1.vegeshop.dto.VegetableRequest;
import com.preworkout.demo1.vegeshop.dto.VegetableResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class VegeService {

    private final VegeRepository vegeRepository;

    public VegeService(VegeRepository vegeRepository) {
        this.vegeRepository = vegeRepository;
    }

    public VegetableResponse save(VegetableRequest vegetableRequest) {
        return VegetableResponse.of(vegeRepository.save(vegetableRequest.toVegetable()));
    }

    @Transactional(readOnly = true)
    public List<VegetableResponse> findAllVegetable() {
        List<Vegetable> vegeList = vegeRepository.findAll();
        return vegeList.stream()
                .map(VegetableResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VegetableResponse findVegetableByName(String name) {
        return VegetableResponse.of(vegeRepository.findByName(name)
                .orElseThrow(NoSuchElementException::new));
    }

}

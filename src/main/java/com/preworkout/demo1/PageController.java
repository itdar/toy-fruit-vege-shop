package com.preworkout.demo1;

import com.preworkout.demo1.fruitshop.application.FruitService;
import com.preworkout.demo1.fruitshop.dto.FruitRequest;
import com.preworkout.demo1.vegeshop.application.VegeService;
import com.preworkout.demo1.vegeshop.dto.VegetableRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class PageController {

    public boolean isFirst = true;

    private final FruitService fruitService;
    private final VegeService vegeService;

    public PageController(FruitService fruitService, VegeService vegeService) {
        this.fruitService = fruitService;
        this.vegeService = vegeService;
    }

    @GetMapping(value = {"/"}, produces = MediaType.TEXT_HTML_VALUE)
    public String index(Model model) {
        if (isFirst) {
            isFirst = false;
            initDatabase();
        }

        model.addAttribute("fruits", fruitService.findAllFruit());
        model.addAttribute("veges", vegeService.findAllVegetable());

        return "index";
    }
    
    private void initDatabase() {
        List<FruitRequest> fruits = Arrays.asList(
                FruitRequest.of("사과", 1000),
                FruitRequest.of("배", 3000),
                FruitRequest.of("수박", 5000));

        List<VegetableRequest> veges = Arrays.asList(
                VegetableRequest.of("치커리", 3400),
                VegetableRequest.of("토마토", 2000),
                VegetableRequest.of("깻잎", 1600),
                VegetableRequest.of("상추", 2500));

        fruits.stream()
                .forEach(fruit -> fruitService.save(fruit));

        veges.stream()
                .forEach(vegetable -> vegeService.save(vegetable));
    }
}

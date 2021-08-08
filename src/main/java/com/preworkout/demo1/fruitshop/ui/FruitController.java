package com.preworkout.demo1.fruitshop.ui;

import com.preworkout.demo1.fruitshop.application.FruitService;
import com.preworkout.demo1.fruitshop.dto.FruitRequest;
import com.preworkout.demo1.fruitshop.dto.FruitResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.preworkout.demo1.auth.domain.AccessToken.ACCESS_TOKEN;

@RestController
@RequestMapping("/fruit")
public class FruitController {
    private final FruitService fruitService;

    public FruitController(final FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<FruitResponse>> findAllFruitOf(@RequestHeader(value="Authorization") String token) {
        if (!token.equals(ACCESS_TOKEN)) {
            throw new IllegalArgumentException("유효하지 않은 token header");
        }
        return ResponseEntity.ok(fruitService.findAllFruit());
    }

    @GetMapping(value = "/product", params = "name")
    public ResponseEntity<FruitResponse> findByName(@RequestHeader(value="Authorization") String token, @RequestParam String name) {
        if (!token.equals(ACCESS_TOKEN)) {
            throw new IllegalArgumentException("유효하지 않은 token header");
        }
        return ResponseEntity.ok(fruitService.findFruitByName(name));
    }

    @PostMapping
    public ResponseEntity<FruitResponse> addFruit(@RequestBody FruitRequest fruitRequest) {
        FruitResponse fruitResponse = fruitService.save(fruitRequest);

        return ResponseEntity.created(URI.create("/fruit/" + fruitResponse.getId())).body(fruitResponse);
    }
}

package com.preworkout.demo1.vegeshop.ui;

import com.preworkout.demo1.vegeshop.application.VegeService;
import com.preworkout.demo1.vegeshop.dto.VegetableRequest;
import com.preworkout.demo1.vegeshop.dto.VegetableResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.preworkout.demo1.auth.domain.AccessToken.ACCESS_TOKEN;

@RestController
@RequestMapping("/vegetable")
public class VegeController {
    private final VegeService vegeService;

    public VegeController(final VegeService vegeService) {
        this.vegeService = vegeService;
    }

    @GetMapping("/item")
    public ResponseEntity<List<VegetableResponse>> findAllVegetableOf(@RequestHeader(value="Authorization") String token) {
        if (!token.equals(ACCESS_TOKEN)) {
            throw new IllegalArgumentException("유효하지 않은 token header");
        }
        return ResponseEntity.ok(vegeService.findAllVegetable());
    }

    @GetMapping(value = "/item", params = "name")
    public ResponseEntity<VegetableResponse> findByName(@RequestHeader(value="Authorization") String token,
                                                        @RequestParam String name) {
        if (!token.equals(ACCESS_TOKEN)) {
            throw new IllegalArgumentException("유효하지 않은 token header");
        }
        return ResponseEntity.ok(vegeService.findVegetableByName(name));
    }

    @PostMapping
    public ResponseEntity<VegetableResponse> addVegetable(@RequestBody VegetableRequest vegetableRequest) {
        VegetableResponse vegetableResponse = vegeService.save(vegetableRequest);

        return ResponseEntity.created(URI.create("/item/" + vegetableResponse.getId())).body(vegetableResponse);
    }
}

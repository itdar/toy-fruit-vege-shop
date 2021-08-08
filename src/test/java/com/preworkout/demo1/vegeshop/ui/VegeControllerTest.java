package com.preworkout.demo1.vegeshop.ui;

import com.preworkout.demo1.auth.domain.AccessToken;
import com.preworkout.demo1.vegeshop.application.VegeService;
import com.preworkout.demo1.vegeshop.domain.VegeRepository;
import com.preworkout.demo1.vegeshop.dto.VegetableRequest;
import com.preworkout.demo1.vegeshop.dto.VegetableResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("야채가게 컨트롤러 응답 테스트클래스")
@DataJpaTest
public class VegeControllerTest {

    private VegeController vegeController;
    private VegeService vegeService;
    @Autowired
    private VegeRepository vegeRepository;

    @BeforeEach
    void setUp() {
        vegeService = new VegeService(vegeRepository);
        vegeController = new VegeController(vegeService);

        // 채소들 등록.
        vegeController.addVegetable(VegetableRequest.of("채소1", 1000));
        vegeController.addVegetable(VegetableRequest.of("채소2", 3000));
        vegeController.addVegetable(VegetableRequest.of("채소3", 5000));
    }

    @DisplayName("이름으로 채소정보를 조회한다.")
    @Test
    public void findByNameTest() {
        ResponseEntity<VegetableResponse> 채소1응답 = vegeController.findByName(AccessToken.ACCESS_TOKEN, "채소1");

        assertThat(채소1응답.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(채소1응답.getBody().getName()).isEqualTo("채소1");
        assertThat(채소1응답.getBody().getPrice()).isEqualTo(1000);
    }

}

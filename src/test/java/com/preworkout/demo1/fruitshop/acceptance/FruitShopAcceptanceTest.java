package com.preworkout.demo1.fruitshop.acceptance;

import com.preworkout.demo1.auth.dto.TokenResponse;
import com.preworkout.demo1.fruitshop.dto.FruitRequest;
import com.preworkout.demo1.fruitshop.dto.FruitResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("과일가게 api 관련 인수테스트 클래스")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FruitShopAcceptanceTest {
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("과일가게 정상동작 케이스")
    @Test
    public void 과일가게_정상동작() {
        // given
        // 과일이 등록되어 있다.
        과일을_등록한다("/fruit", FruitRequest.of("사과", 1000)).as(FruitResponse.class);
        과일을_등록한다("/fruit", FruitRequest.of("배", 3000)).as(FruitResponse.class);
        과일을_등록한다("/fruit", FruitRequest.of("수박", 5000)).as(FruitResponse.class);

        // when
        // 토큰을 발급 받는다.
        TokenResponse tokenResponse = 과일가게_토큰을_요청한다("/token").as(TokenResponse.class);
        // then
        // 토큰이 발급 된다.
        String accessToken = tokenResponse.getAccessToken();

        // when
        // 과일 목록을 조회한다. with token
        ExtractableResponse<Response> fruitResponses = 과일목록을_토큰을써서_가져온다("fruit/product", accessToken);
        // then
        // 등록된 과일들 이름을 반환한다
        List<String> resultResponses = fruitResponses.jsonPath().getList(".", FruitResponse.class).stream()
                .map(FruitResponse::getName)
                .collect(Collectors.toList());
        assertThat(resultResponses).containsAll(Arrays.asList("사과", "배", "수박"));

        // when
        // 이름으로 과일 가격을 조회한다. with token
        ExtractableResponse<Response> fruitResponse
                = 과일정보를_토큰을써서_가져온다("fruit/product", FruitRequest.of("사과", 1000), accessToken);
        // then
        // 과일 이름과 가격 반환한다.
        assertThat(fruitResponse.as(FruitResponse.class).getName()).isEqualTo("사과");
    }

    @DisplayName("과일가게 토큰없이 요청한다.")
    @Test
    public void 과일가게_비정상동작_토큰없음() {
        // given
        // 과일이 등록되어 있다.

        // when
        // 과일 목록을 조회한다.
        // then
        // 토큰이 없어서 400 응답

        // when
        // 등록되어있는 과일 이름 조회한다.
        // then
        // 토큰이 없어서 400 응답

        // when
        // 등록되어있는 과일 이름 조회한다.
        // then
        // 토큰이 없어서 400 응답
    }

    @DisplayName("과일가게 등록되지 않은 과일을 조회한다.")
    @Test
    public void 과일가게_비정상동작_과일없음() {
        // given
        // 과일이 등록되어 있다.

        // when
        // 과일 목록을 조회한다.
        // then
        // 토큰이 없어서 400 응답

        // when
        // 등록되어있는 과일 이름 조회한다.
        // then
        // 토큰이 없어서 400 응답

        // when
        // 등록되어있는 과일 이름 조회한다.
        // then
        // 토큰이 없어서 400 응답
    }

    private static ExtractableResponse<Response> 과일을_등록한다(String path, FruitRequest request) {
        return RestAssured.given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(path)
                .then().log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 과일가게_토큰을_요청한다(String path) {
        return RestAssured.given().log().all()
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 과일목록을_토큰을써서_가져온다(String path, String token) {
        return RestAssured.given().log().all()
                .header("Authorization", token)
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 과일정보를_토큰을써서_가져온다(String path, FruitRequest request, String token) {
        return RestAssured.given()
                .params("name", request.getName())
                .log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }
}

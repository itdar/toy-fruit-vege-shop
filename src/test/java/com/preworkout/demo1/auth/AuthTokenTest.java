package com.preworkout.demo1.auth;

import com.preworkout.demo1.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("인증 토큰 인수테스트")
public class AuthTokenTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("채소 토큰을 쿠키로 발급 받는다.")
    @Test
    public void getVegeTokenTest() {
        // given
        // 토큰이 저장되어있다.

        // when
        // 토큰을 요청한다.
        ExtractableResponse<Response> response = 토큰을_요청한다("/vegetoken");

        // then
        // 토큰이 쿠키에 담겨져서 온다.
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

    }

    private static ExtractableResponse<Response> 토큰을_요청한다(String path) {
        return RestAssured.given().log().all()
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

}

package com.preworkout.demo1.fruitshop.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class FruitTest {

    @Test
    public void create() {
        Fruit fruit = Fruit.of("사과", 1000);

        assertThat(fruit.name()).isEqualTo("사과");
        assertThat(fruit.price()).isEqualTo(1000);
    }

    @DisplayName("음수의 가격을 넣어서 과일을 만든다")
    @Test
    public void 유효하지않은_금액의_과일() {
        assertThatThrownBy(() -> {
            Fruit fruit = Fruit.of("사과", -1000);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaddleModelTest {
    private PaddleModel paddle;

    @BeforeEach
    void init(){
        paddle = new PaddleModel();
    }

    @Test
    void changePaddleSize() {
    paddle.width = 100;
    paddle.changePaddleSize();

    assertEquals(200, paddle.width);

    paddle.changePaddleSize();

    assertEquals(100, paddle.width);

    }
}
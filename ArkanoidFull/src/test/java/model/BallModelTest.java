package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallModelTest {
    private BallModel ball;

    @BeforeEach
    void init(){
        ball = new BallModel();
    }

    @Test
    void createBalls() {
        int ballCounter = 0;
        for(int i = 0; i < 10; i++){
            if (ball.ballArray[i][4] == 0){
                ballCounter++;
            }

        }

        assertEquals(ball.ballsLeft, ballCounter);
    }

    @Test
    void fasterBalls() {
        int[][] balls = new int[10][2];
        for(int i = 0; i < 10; i++){
            balls[i][0] = ball.ballArray[i][2];
            balls[i][1] = ball.ballArray[i][3];
        }

        ball.fasterBalls();

        for(int i = 0; i < 10; i++){
            assertEquals(balls[i][0]*2, ball.ballArray[i][2]);
            assertEquals(balls[i][1]*2, ball.ballArray[i][3]);

        }

    }

}
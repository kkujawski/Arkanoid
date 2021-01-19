package model;
import controller.Controller;
import view.View;
import model.PaddleModel;
import java.awt.*;

public class BallModel {
    public static int radius = 20;
    public static int [][] ballArray= new int[10][5]; // [ballX, ballY, speedX, speedY, active]
    int ballsLeft = 1;

    public BallModel(){
        createBalls();
    }

    public void createBalls(){
        //Clears ball array and creates one ball with default values

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 4; j++){
                ballArray[i][j] = 0;

            }
            ballArray[i][4] = 1;

        }

        ballArray[0][0] = 300;
        ballArray[0][1] = 300;
        ballArray[0][2] = 2;
        ballArray[0][3] = 4;
        ballArray[0][4] = 0;
        ballsLeft = 1;

    }

    public void moveBalls(){
        for(int ball = 0; ball < 10; ball++){
            if(ballArray[ball][4] != 1){
                // ball position += ball speed
                ballArray[ball][0] += ballArray[ball][2];
                ballArray[ball][1] += ballArray[ball][3];

                //Checking if ball is not at screen borders
                if(ballArray[ball][0] < 0) {
                    ballArray[ball][2] = -ballArray[ball][2];

                }

                if(ballArray[ball][1] < 0) {
                    ballArray[ball][3] = -ballArray[ball][3];


                }

                if(ballArray[ball][0] >= 560) {
                    ballArray[ball][2] = -ballArray[ball][2];

                }

            }

        }

    }


    public void ballHitBlock(BlockModel b){
        //Checks if any active ball intersects with any active block
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 6; col++){
                for(int ball = 0; ball < 10; ball++) {

                    if(ballArray[ball][4] != 1 ) {
                        if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(100 + col * 70, 50 + row * 30, 50, 20)) && !b.isHit(row, col)) {
                            b.blocksLeft--;
                            b.blockArray[row][col][0] = 0;
                            //If the block has powerups do them
                            if (b.blockArray[row][col][1] == 1){
                                PaddleModel.changePaddleSize();

                            } else if (b.blockArray[row][col][1] == 2){
                                fasterBalls();

                            } else if (b.blockArray[row][col][1] == 3){
                                moreBalls();

                            } else if (b.blockArray[row][col][1] == 4){
                                slowerBalls();

                            }
                            //Bounces the ball
                            if (ballArray[ball][0] <= 100 + 50 + ballArray[ball][2] - 1 + col * 70 && ballArray[ball][0] >= 100 - 15 + ballArray[ball][2] + 1 + col * 70) {
                                ballArray[ball][3] = -1 * ballArray[ball][3];

                            } else {
                                ballArray[ball][2] = -1 * ballArray[ball][2];

                            }

                        }

                    }

                }

            }

        }

    }


    public void ballHitPaddle(PaddleModel p){
        //Checking if any ball hit the paddle
        for(int ball = 0; ball < 10; ball++) {
            if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(p.paddleX, 450, p.width, 10)) && ballArray[ball][4] != 1) {
                ballArray[ball][3] = -ballArray[ball][3];

                //Which side of the paddle hit
                if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(p.paddleX, 450, p.width/2, 10)) && ballArray[ball][4] != 1) {
                    ballArray[ball][2] -= 1;

                }
                if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(p.paddleX + p.width/2, 450, p.width/2, 10)) && ballArray[ball][4] != 1){
                    ballArray[ball][2] += 1;

                }

            }

        }

    }


    public void fasterBalls(){
        for(int ball = 0; ball < 10; ball++){
            ballArray[ball][2] *= 2;
            ballArray[ball][3] *= 2;

        }

    }

    public void slowerBalls(){
        for(int ball = 0; ball < 10; ball++){
            ballArray[ball][2] /= 2;
            ballArray[ball][3] /= 2;

            if (ballArray[ball][3] == 0)
                ballArray[ball][3] = 1;

        }

    }

    public void moreBalls(){

        if(ballsLeft > 4){
            //If there are 5 or more balls, activates all 10 balls
            ballsLeft = 10;
            for(int ball = 0; ball < 10; ball++){
                if(ballArray[ball][4] == 1){
                    ballArray[ball][0] = 300;
                    ballArray[ball][1] = 300;

                    while (ballArray[ball][2] == 0 || ballArray[ball][3] == 0) {
                        ballArray[ball][2] = Controller.randomNumber.nextInt(10) - 5;
                        ballArray[ball][3] = Controller.randomNumber.nextInt(10) - 5;

                    }

                    ballArray[ball][4] = 0;
                }

            }

        } else {
            //Doubles number of balls
            int i = ballsLeft;
            ballsLeft *= 2;
            for(int ball = 0; ball <10; ball++){

                if(ballArray[ball][4] == 1){
                    ballArray[ball][0] = 300;
                    ballArray[ball][1] = 300;

                    while (ballArray[ball][2] == 0 || ballArray[ball][3] == 0) {
                        ballArray[ball][2] = Controller.randomNumber.nextInt(10) - 5;
                        ballArray[ball][3] = Controller.randomNumber.nextInt(10) - 5;

                    }
                    ballArray[ball][4] = 0;
                    i--;
                    if(i <= 0){
                        break;

                    }

                }

            }

        }

    }

    public void ballIsOut(){
        //Checks if any ball has fallen off the screen
        for (int i = 0; i < 10; i++) {

            if (ballArray[i][1] > 510 && ballArray[i][4] != 1) {
                if(ballsLeft == 1) {
                    if (Controller.lives > 1) {
                        Controller.lives--;
                        createBalls();
                        PaddleModel.setPaddleWidth(100);

                    } else {
                        Controller.lost = true;
                        Controller.active = false;

                    }
                } else {
                    ballArray[i][4] = 1;
                    ballsLeft--;

                }

            }

        }

    }





}

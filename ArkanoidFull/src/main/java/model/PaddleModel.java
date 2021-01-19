package model;

public class PaddleModel {
    public static int width = 100, height = 10, paddleX = 250;
    private int paddleSpeed = 0;
    public PaddleModel(){

    }
    public void moveLeft() {
        paddleSpeed = -10;

    }

    public void moveRight() {
        paddleSpeed = 10;

    }

    public void stopPaddle(){
        paddleSpeed = 0;

    }

    public static void changePaddleSize(){
        if (width < 150)
            width = 200;

        else
            width = 100;

    }
    public static void setPaddleWidth(int number){
        width = number;
    }


    public void movePaddle(){
        //Checks if paddle is not at the borders of the screen
        if (!(paddleX + paddleSpeed > 600 - width) && !(paddleX + paddleSpeed < 0)){
            paddleX += paddleSpeed;

        }else if (paddleX + paddleSpeed > 600 - width){
            paddleX = 600 - width;

        } else {
            paddleX = 0;

        }
    }
}

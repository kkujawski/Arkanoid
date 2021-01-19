package model;

public class PaddleModel {
    public static int width = 100, height = 10, x = 250, y = 450;
    public PaddleModel(){

    }

    public void moveRight(){
        if (x >= 490) {
            x = 490;
        } else {
            x += 10;
        }
    }

    public void moveLeft(){
        if (x <= 10) {
            x = 0;
        } else {
            x -= 10;
        }
    }

}

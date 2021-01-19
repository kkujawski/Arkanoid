package model;
import view.View;
import model.PaddleModel;
import java.awt.*;

public class BallModel {
    public static int x, y, speedX, speedY;
    public static int radius = 20;





    public BallModel(){
        x = 400;
        y = 400;
        speedX = 1;
        speedY = 1;
    }

    public int getBallX(){
        return x;

    }

    public int getgBallY(){
        return y;

    }

    public int getBallRadius(){
        return radius;
    }


    public void bounceX(){
        speedX = -speedX;

    }

    public void bounceY(){
        speedY = -speedY;

    }

    public void updateBallPosition(){

        if (x <= 0 || x >= 600){
            bounceX();
        }
        if(y <= 0 ){
            bounceY();
        }


        x += speedX;
        y += speedY;


    }



}

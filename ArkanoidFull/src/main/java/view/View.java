package view;
import javax.swing.*;
import model.*;

import java.awt.*;

public class View extends JPanel {
    public static int width = 600, height = 500;

    public View(){
        JFrame window = new JFrame();
        window.setBounds(10, 10, width, height);
        window.setTitle("Arkanoid");
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
                System.out.print("kurczak");

        //Coloring background
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 600, 500);
        //Coloring ball
        g2.setColor(Color.red);
        g2.fillOval(BallModel.x, BallModel.y, BallModel.radius, BallModel.radius);
        //Coloring paddle
        g2.setColor(Color.black);
        g2.fillRect(PaddleModel.x, PaddleModel.y, PaddleModel.width, PaddleModel.height);
        //Coloring block if its not hit
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                if(!BlockModel.isHit(row, col)) {
                    g2.setColor(Color.cyan);
                    g2.fillRect(275, 100, 50, 20);

                }

            }

        }
        g2.dispose();

    }


//    public void updateScreen(){
//        this.repaint();
//        System.out.print("kurczak");
//
//    }



}

package view;
import javax.swing.*;

import controller.Controller;
import model.*;

import java.awt.*;

public class View extends JFrame {
    public static int width = 600, height = 500;
    private final String os = System.getProperty("os.name").toLowerCase();
    BallModel ball;
    BlockModel block;
    PaddleModel paddle;
    public View(BallModel ball, PaddleModel paddle, BlockModel block){
        this.ball = ball;
        this.block = block;
        this.paddle = paddle;
        this.setBounds(10,10,width,height);
        this.setTitle("Arkanoid");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);



        this.add(new JPanel () {
            @Override
            public void paint(Graphics g){
                if (!Controller.win&&!Controller.lost) {
                //Coloring background
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 500);

                //Coloring balls
                for(int i = 0; i < 10; i++) {
                    if(BallModel.ballArray[i][4] != 1) {
                        g.setColor(Color.red);
                        g.fillOval(BallModel.ballArray[i][0], BallModel.ballArray[i][1], 20, 20);

                    }

                }

                //Coloring paddle
                g.setColor(Color.black);
                g.fillRect(PaddleModel.paddleX, 450, PaddleModel.width, 10);

                //Coloring blocks if they are not hit
                for (int row = 0; row < 6; row++) {
                    for (int col = 0; col < 6; col++) {
                        if (!BlockModel.isHit(row, col)) {

                            //Colors accordingly to powerups
                            if (BlockModel.blockArray[row][col][1] == 0) {
                                g.setColor(Color.cyan);
                                g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                            } else if (BlockModel.blockArray[row][col][1] == 1){
                                g.setColor(Color.red);
                                g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                            } else if (BlockModel.blockArray[row][col][1] == 2){
                                g.setColor(Color.green);
                                g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                            } else if (BlockModel.blockArray[row][col][1] == 3){
                                g.setColor(Color.blue);
                                g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                            } else if (BlockModel.blockArray[row][col][1] == 4){
                                g.setColor(Color.orange);
                                g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                            }

                        }

                    }

                }
                g.setColor(Color.black);
                g.drawString("Lives: "+ Controller.lives, 10, 10);

                if(!Controller.active){
                    //Displays while display is not active
                    g.drawString("To play press space.", 200, 250);
                    g.drawString("Blue - more balls.", 200, 260);
                    g.drawString("Red - change paddle size.", 200, 270);
                    g.drawString("Orange - slower balls.", 200, 280);
                    g.drawString("Green - faster balls.", 200, 290);

                }

            }

                if(Controller.win){
                    //Display after win
                    g.setColor(Color.white);
                    g.fillRect(0, 0, 600, 500);
                    g.setColor(Color.black);
                    g.drawString("Congratulations you won!!!", 200, 200);
                    g.drawString("To play again press space.", 200, 250);

                }

                if(Controller.lost){
                    //Display after loss
                    g.setColor(Color.white);
                    g.fillRect(0, 0, 600, 500);
                    g.setColor(Color.black);
                    g.drawString("You lost :(", 200, 200);
                    g.drawString("To play again press space.", 200, 250);

                }
                g.dispose();


            }
        });

    }




    public void updateScreen(){
        if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
            Toolkit.getDefaultToolkit().sync();
        }
        this.repaint();

    }



}
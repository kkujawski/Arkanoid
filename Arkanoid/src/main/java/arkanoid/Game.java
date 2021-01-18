package arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {

    private boolean active = false, lost = false, win = false;
    private Timer timer;
    private int delay = 20;
    Random randomNumber = new Random();
    public int blocksLeft = randomNumber.nextInt(15 + 1) + 10;
    public static int[][][] blockArray = new int[6][6][2]; // [][][isHit, powerups]
    //Starting positions
    private int paddleX = 250, paddleSpeed = 0;

    int paddleWidth = 100;
    int lives = 3;
    int [][] ballArray= new int[10][5]; // [ballX, ballY, speedX, speedY, active]
    int ballsLeft = 1;

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

    public void createBlocks(){
        //Clears block array and generates new one
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                for (int k = 0; k < 2; k++){
                    blockArray[i][j][k] = 0;

                }

            }

        }

        //Chooses random number of blocks between 10 and 36
        blocksLeft = randomNumber.nextInt(26 + 1) + 10;
        int i = blocksLeft;
        int row, col, powerup;
        while (i > 0){
            //Get random column and row number
            row = randomNumber.nextInt(6);
            col = randomNumber.nextInt(6);
            powerup = randomNumber.nextInt(10);
            //Check if there is already a block
            if (blockArray[row][col][0] != 1){

                blockArray[row][col][0] = 1;
                if(powerup == 1){
                    blockArray[row][col][1] = 1;

                } else if (powerup == 2){
                    blockArray[row][col][1] = 2;

                } else if (powerup == 3){
                    blockArray[row][col][1] = 3;

                } else if (powerup == 4){
                    blockArray[row][col][1] = 4;

                }

                i--;

            }

        }

    }

    public static boolean isHit(int row, int col){
        return blockArray[row][col][0] == 0;

    }

    public Game () {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        createBalls();
        createBlocks();

    }

    void didWin(){
        if (blocksLeft <= 0){
            win = true;
            active = false;

        }

    }

    void ballHitBlock(){
        //Checks if any active ball intersects with any active block
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 6; col++){
                for(int ball = 0; ball < 10; ball++) {

                    if(ballArray[ball][4] != 1 ) {
                        if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(100 + col * 70, 50 + row * 30, 50, 20)) && !isHit(row, col)) {
                            blocksLeft--;
                            blockArray[row][col][0] = 0;
                            //If the block has powerups do them
                            if (blockArray[row][col][1] == 1){
                                changePaddleSize();

                            } else if (blockArray[row][col][1] == 2){
                                fasterBalls();

                            } else if (blockArray[row][col][1] == 3){
                                moreBalls();

                            } else if (blockArray[row][col][1] == 4){
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

    void ballHitPaddle(){
        //Checking if any ball hit the paddle
        for(int ball = 0; ball < 10; ball++) {
            if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(paddleX, 450, paddleWidth, 10)) && ballArray[ball][4] != 1) {
                ballArray[ball][3] = -ballArray[ball][3];

                //Which side of the paddle hit
                if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(paddleX, 450, paddleWidth/2, 10)) && ballArray[ball][4] != 1) {
                    ballArray[ball][2] -= 1;

                }
                if (new Rectangle(ballArray[ball][0], ballArray[ball][1], 20, 20).intersects(new Rectangle(paddleX + paddleWidth/2, 450, paddleWidth/2, 10)) && ballArray[ball][4] != 1){
                    ballArray[ball][2] += 1;

                }

            }

        }

    }

    void changePaddleSize(){
        if (paddleWidth < 150)
            paddleWidth = 200;

        else
            paddleWidth = 100;

    }

    void fasterBalls(){
        for(int ball = 0; ball < 10; ball++){
            ballArray[ball][2] *= 2;
            ballArray[ball][3] *= 2;

        }

    }

    void slowerBalls(){
        for(int ball = 0; ball < 10; ball++){
            ballArray[ball][2] /= 2;
            ballArray[ball][3] /= 2;

            if (ballArray[ball][3] == 0)
                ballArray[ball][3] = 1;

        }

    }

    void moreBalls(){

        if(ballsLeft > 4){
            //If there are 5 or more balls, activates all 10 balls
            ballsLeft = 10;
            for(int ball = 0; ball < 10; ball++){
                if(ballArray[ball][4] == 1){
                    ballArray[ball][0] = 300;
                    ballArray[ball][1] = 300;

                    while (ballArray[ball][2] == 0 || ballArray[ball][3] == 0) {
                        ballArray[ball][2] = randomNumber.nextInt(10) - 5;
                        ballArray[ball][3] = randomNumber.nextInt(10) - 5;

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
                        ballArray[ball][2] = randomNumber.nextInt(10) - 5;
                        ballArray[ball][3] = randomNumber.nextInt(10) - 5;

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


    void ballIsOut(){
        //Checks if any ball has fallen off the screen
        for (int i = 0; i < 10; i++) {

            if (ballArray[i][1] > 510 && ballArray[i][4] != 1) {
                if(ballsLeft == 1) {
                    if (lives > 1) {
                        lives--;
                        createBalls();
                        paddleWidth = 100;

                    } else {
                        lost = true;
                        active = false;

                    }
                } else {
                    ballArray[i][4] = 1;
                    ballsLeft--;

                }

            }

        }

    }

    void resetGame(){
        lives = 3;
        win = false;
        paddleWidth = 100;
        lost = false;
        createBalls();
        createBlocks();

    }

    public void paint(Graphics g) {
        if (!win&&!lost) {
            //Coloring background
            g.setColor(Color.white);
            g.fillRect(0, 0, 600, 500);

            //Coloring balls
            for(int i = 0; i < 10; i++) {
                if(ballArray[i][4] != 1) {
                    g.setColor(Color.red);
                    g.fillOval(ballArray[i][0], ballArray[i][1], 20, 20);

                }

            }

            //Coloring paddle
            g.setColor(Color.black);
            g.fillRect(paddleX, 450, paddleWidth, 10);

            //Coloring blocks if they are not hit
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    if (!isHit(row, col)) {

                        //Colors accordingly to powerups
                        if (blockArray[row][col][1] == 0) {
                            g.setColor(Color.cyan);
                            g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                        } else if (blockArray[row][col][1] == 1){
                            g.setColor(Color.red);
                            g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                        } else if (blockArray[row][col][1] == 2){
                            g.setColor(Color.green);
                            g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                        } else if (blockArray[row][col][1] == 3){
                            g.setColor(Color.blue);
                            g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                        } else if (blockArray[row][col][1] == 4){
                            g.setColor(Color.orange);
                            g.fillRect(100 + col * 70, 50 + row * 30, 50, 20);

                        }

                    }

                }

            }
            g.setColor(Color.black);
            g.drawString("Lives: "+lives, 10, 10);

            if(!active){
                //Displays while display is not active
                g.drawString("To play press space.", 200, 250);
                g.drawString("Blue - more balls.", 200, 260);
                g.drawString("Red - change paddle size.", 200, 270);
                g.drawString("Orange - slower balls.", 200, 280);
                g.drawString("Green - faster balls.", 200, 290);

            }

        }

        if(win){
            //Display after win
            g.setColor(Color.white);
            g.fillRect(0, 0, 600, 500);
            g.setColor(Color.black);
            g.drawString("Congratulations you won!!!", 200, 200);
            g.drawString("To play again press space.", 200, 250);

        }

        if(lost){
            //Display after loss
            g.setColor(Color.white);
            g.fillRect(0, 0, 600, 500);
            g.setColor(Color.black);
            g.drawString("You lost :(", 200, 200);
            g.drawString("To play again press space.", 200, 250);

        }
        g.dispose();

    }

    public void moveLeft() {
        paddleSpeed = -10;

    }

    public void moveRight() {
        paddleSpeed = 10;

    }

    void stopPaddle(){
        paddleSpeed = 0;

    }

    void movePaddle(){
        //Checks if paddle is not at the borders of the screen
        if (!(paddleX + paddleSpeed > 600 - paddleWidth) && !(paddleX + paddleSpeed < 0)){
            paddleX += paddleSpeed;

        }else if (paddleX + paddleSpeed > 600 - paddleWidth){
            paddleX = 600 - paddleWidth;

        } else {
            paddleX = 0;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (active && e.getSource() == timer) {

            //Checking if any events happen on screen
            ballHitPaddle();
            ballHitBlock();
            ballIsOut();

            //Checking win condition
            didWin();

            //Moving the paddle
            movePaddle();

            //Moving ball
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
            repaint();

        }


    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Checking if navigation keys are pressed
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft();

        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight();

        }

        //Starting game with spacebar
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            active = true;
            if(lost || win){
                resetGame();

            }
        }

        //Reseting game with escape button
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
            active = false;
            repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Stopping the paddle when keys are lifted
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            stopPaddle();

        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            stopPaddle();

        }

    }

}


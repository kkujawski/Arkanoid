package controller;

import view.View;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Controller implements KeyListener, ActionListener {
    BallModel ball;
    BlockModel block;
    PaddleModel paddle;
    View view;
    Timer timer;
    public static int lives = 3;
    public static Random randomNumber = new Random();
    public static boolean active = false, win = false, lost = false;
    int delay = 30;


    public Controller(BallModel ball, BlockModel block, PaddleModel paddle, View screen) {
        System.out.print("dziala");
        this.ball = ball;
        this.block = block;
        this.paddle = paddle;
        this.view = screen;
        timer = new Timer(delay, this);
        timer.start();
    }

    void resetGame(){
        lives = 3;
        win = false;
        paddle.width = 100;
        lost = false;
        ball.createBalls();
        block.createBlocks();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (active && e.getSource() == timer){

            //Checking if any events happen on screen
            ball.ballHitPaddle(paddle);
            ball.ballHitBlock(block);
            ball.ballIsOut();

            //Checking win condition
            block.didWin();

            //Moving the paddle
            paddle.movePaddle();

            //Moving balls
            ball.moveBalls();

            view.updateScreen();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Checking if navigation keys are pressed
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
            System.out.print("lewo");

        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
            System.out.print("prawo");

        }

        //Starting game with spacebar
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            active = true;
            System.out.print("spacja");
            if(lost || win){
                resetGame();

            }
        }

        //Reseting game with escape button
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
            active = false;
            view.updateScreen();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Stopping the paddle when keys are lifted
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.stopPaddle();

        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.stopPaddle();

        }
    }
}

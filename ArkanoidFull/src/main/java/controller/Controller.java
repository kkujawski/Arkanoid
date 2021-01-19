package controller;

import view.View;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends JPanel implements KeyListener, ActionListener {
    BallModel ballModel;
    BlockModel blockModel;
    PaddleModel paddleModel;
    View view;
    Timer timer;
    boolean active = false;
    boolean win = false;
    int delay = 16;


    public Controller(BallModel ball, BlockModel block, PaddleModel paddle, View screen) {
        this.ballModel = ball;
        this.blockModel = block;
        this.paddleModel = paddle;
        this.view = screen;
        timer = new Timer(delay, this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer){
            this.view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package arkanoid;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //Creates new Game object
        Game gameController = new Game();
        /* Creates screen and assigns controller to it */
        JFrame obj = new JFrame();
        obj.setBounds(10,10,600,500);
        obj.setTitle("Arkanoid");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameController);
    }
}

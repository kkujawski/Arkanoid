import model.*;
import controller.Controller;
import view.View;



public class Arkanoid {
    public static void main(String[] args) {
        BallModel ball = new BallModel();
        BlockModel block = new BlockModel();
        PaddleModel paddle = new PaddleModel();
        View screen = new View(ball, paddle, block);
        Controller controller = new Controller(ball, block, paddle, screen);
        screen.addKeyListener(controller);

    }
}

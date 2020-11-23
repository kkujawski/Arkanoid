package arkanoid;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game gameControler = new Game();
		JFrame obj = new JFrame();
		obj.setBounds(10,10,600,500);
		obj.setTitle("Arkanoid");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameControler);
	}

}

package BrickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		Game game = new Game();
		
		// window settings
		window.setBounds(10,10,700,600);
		window.setTitle("Breakout");
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(game);// adds game class to window
		
	}// end of method

}// end of class

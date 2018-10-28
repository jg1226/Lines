import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		Lines game = new Lines();
			// window settings
			window.setBounds(10,10,700,600);
			window.setTitle("Lines");
			window.setResizable(false);
			window.setVisible(true);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.add(game);// adds game class to window
		
	}// end of method
	
}// end of main


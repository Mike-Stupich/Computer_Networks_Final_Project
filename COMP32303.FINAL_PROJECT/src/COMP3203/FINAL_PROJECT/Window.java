package COMP3203.FINAL_PROJECT;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	private static int HEIGHT = 400, WIDTH=500;
	private Client client;
	public Window(String title, String args[]){
		//super(title);
		//add(client = new Client());
	}
	public static void createFrame(String args[]){
		//Component component = new test();
		//component.createImage(800, 500);
		JFrame window = new JFrame("Simulation");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.getContentPane().add(component,  BorderLayout.CENTER);
		window.pack();
		window.setVisible(true);
		//window.setDefaultLookAndFeelDecorated(true);
		
		
		
		//JPanel panel1 = new JPanel(new BorderLayout());
		
		
	}

	

}

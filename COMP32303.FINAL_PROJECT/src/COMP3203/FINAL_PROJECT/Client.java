package COMP3203.FINAL_PROJECT;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSlider;

public class Client {

	public static Logger log = Logger.getLogger(Client.class.getName());
	private JFrame frame;
	private DataContainer data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client("COMP 3203 FINAL PROJECT");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client(String winName) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		data = new DataContainer();
		JSlider slider = new JSlider();
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnRigid = new JButton("Rigid");
		btnRigid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.getValue();
				log.info(""+slider.getValue());
			}
		});
		panelSouth.add(btnRigid);
		
		JButton btnSimple = new JButton("Simple");
		btnSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.getValue();
				log.info(""+slider.getValue());
			}
		});
		panelSouth.add(btnSimple);
		
		JButton btnCustom = new JButton("Custom");
		btnCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.getValue();
				log.info(""+slider.getValue());
			}
		});
		panelSouth.add(btnCustom);
		
		
		JButton btnGraph = new JButton("Graph");
		panelSouth.add(btnGraph);
		
		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);

		slider.setSnapToTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.setToolTipText("Number of Beacons");
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMaximum(50);
		panelCenter.add(slider, BorderLayout.CENTER);
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		slider.getValue();
		log.info(""+slider.getValue());
		
		JPanel panelNorth = new JPanel();
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
	}
	
}

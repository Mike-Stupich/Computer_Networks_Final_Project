package COMP3203.FINAL_PROJECT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Client extends JFrame{
	private static final long serialVersionUID = 1L;
	public static int DEFAULT_WIDTH = 800, DEFAULT_HEIGHT = 600;
	public static int MAX_BEACONS = 50, MAX_RADIUS = 10, LINE_SCALE = 7;
	public static int DATA_RANGE = 20;
	public static Logger log = Logger.getLogger(Client.class.getName());
	private View view;
	protected static String algChoice = "Simple";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		}
		catch(Exception e){
			
		}
		
		new Client("COMP 3203 FINAL PROJECT").setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public Client(String winName) {
		super(winName);
		add(view = new View());
		handleEvents();
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		view.getDisplay().getCreateButton().doClick();
	}
	
	protected void handleEvents() {		
		view.getDisplay().getCreateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				handleCreate();
				view.getDisplay().btnStart.setEnabled(true);
			}
		});
		
		view.getDisplay().getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				handleStart();
				view.getDisplay().btnStart.setEnabled(false);
			}
		});
		view.getDisplay().getSimpleButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				algChoice = "Simple";
				handleStart();
			}
		});
		
		view.getDisplay().getRigidButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				algChoice = "Rigid";
				handleStart();
			}
		});	
		
		view.getDisplay().getCustomAlgButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				algChoice = "CustomAlg";
				handleStart();
			}
		});
		
		view.getDisplay().getGraphButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				handleGraph();
			}
		});
	}
	
	protected void handleStart() {
		log.info("N=" + Display.CURRENT_BEACONS + ", R=" + Display.CURRENT_RADIUS + " (in relation to interval [0,1]: " + Display.CURRENT_RADIUS/100f + ")");
		view.getData().start(algChoice);
	}
	
	protected void handleCreate() {
		int beacons = view.getDisplay().getBeaconSlider().getValue();
		int radius = view.getDisplay().getRadiusSlider().getValue();
		
		if(radius < 1) {
			showAlert("Radius must be larger than zero.");
		} else {
			view.getData().create(algChoice, beacons, radius);
		}
	}
	
	protected void handleGraph(){
		int beacons = view.getDisplay().getBeaconSlider().getValue();
		int radius = view.getDisplay().getRadiusSlider().getValue();
		for(int i = 1; i <= Display.RADIUS_MAX; ++i){
			view.getDisplay().getRadiusSlider().setValue(i);
			radius = view.getDisplay().getRadiusSlider().getValue();
			for(int j = 0; j < DATA_RANGE; ++j){
				view.getData().create(algChoice, beacons, radius);
				view.getData().start(algChoice);
			}
		}
		//Create graph
	}
	

	public boolean showAlert(String message) {
		JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
		return false;
	}
}

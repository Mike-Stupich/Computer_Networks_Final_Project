package COMP3203.FINAL_PROJECT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Client extends JFrame{
	private static final long serialVersionUID = 1L;
	public static int DEFAULT_WIDTH = 800, DEFAULT_HEIGHT = 600;	//Size of window
	public static int MAX_BEACONS = 50;
	public static int MAX_RADIUS = 10;		
	public static int LINE_SCALE = 7;		//Size of the beacons and line in the window
	public static int DATA_RANGE = 5;		//Sample size to determine average # moves
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
				setButtons(true);
			}
		});

		view.getDisplay().getSimpleButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				algChoice = "Simple";
				handleStart();
				setButtons(false);
			}
		});
		
		view.getDisplay().getRigidButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				algChoice = "Rigid";
				handleStart();
				setButtons(false);
			}
		});	
		
		view.getDisplay().getCustomAlgButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				algChoice = "CustomAlg";
				handleStart();
				setButtons(false);
			}
		});
		
		view.getDisplay().getGraphButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ChartData.clearData();
				handleGraph();
			}
		});
	}
	
	protected void setButtons(boolean onOff){
		view.getDisplay().getSimpleButton().setEnabled(onOff);
		view.getDisplay().getRigidButton().setEnabled(onOff);
		view.getDisplay().getCustomAlgButton().setEnabled(onOff);
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
		Map <Integer, List<Double>> map = new HashMap<Integer,List<Double>>();
		List<Double> list = new ArrayList<Double>();
		DataComponent.animate = false;
		int beacons = view.getDisplay().getBeaconSlider().getValue();
		log.info("Current Alg Choice: " + algChoice);
		for(int i = 1; i <= Display.RADIUS_MAX; ++i){
			view.getDisplay().getRadiusSlider().setValue(i);
			int radius = view.getDisplay().getRadiusSlider().getValue();
			for(int j=0; j<DATA_RANGE; ++j){
				view.getData().create(algChoice, beacons, radius);
				view.getData().start(algChoice);
				//Gets the sum of movements from the algorithms
				list.add((double)DataComponent.SUM/Display.CURRENT_BEACONS);
			}
			//Puts the list of doubles in the map, calculates the average later
			map.put(i, list);
			
		}
		DataComponent.animate = true;
		new CreateGraph("COMP3203 Final Project" ,"Radius vs Number of Movements of "+Display.CURRENT_BEACONS+" Beacons using "+algChoice + " Algorithm", map).setVisible(true);
	}
	

	public boolean showAlert(String message) {
		JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
		return false;
	}
}

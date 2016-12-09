package COMP3203.FINAL_PROJECT;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class DataContainer extends JPanel{
	protected ArrayList<Beacon> BeaconList;
	protected int radius;
	protected Random rand;
	
	
	public DataContainer(){
		BeaconList = new ArrayList<Beacon>();
		rand = new Random();
		initLayout();
		
	}
	
	public void initLayout(){
		setBackground(Color.WHITE);
	}
}

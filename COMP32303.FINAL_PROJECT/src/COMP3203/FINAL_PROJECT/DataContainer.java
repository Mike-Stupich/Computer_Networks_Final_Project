package COMP3203.FINAL_PROJECT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class DataContainer extends JPanel{
	
	
	protected ArrayList<Beacon> BeaconList;
	protected int radius;
	protected Random rand;
	public final static int MAX_TRACE_LEN = 100 * Client.LINE_SCALE;
	public final static int CENTER_Y = 135;
	public static int WIDTH, HEIGHT;
	public static int offsetX;
	
	public DataContainer(){
		BeaconList = new ArrayList<Beacon>();
		rand = new Random();
		initLayout();
		
	}
	
	public void initLayout(){
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics beacon){
		super.paintComponent(beacon);
		Graphics2D drawBeacon = (Graphics2D) beacon;
		
		drawL(drawBeacon, new Point((Client.DEFAULT_WIDTH - MAX_TRACE_LEN)/2, CENTER_Y), 
			new Point(Client.DEFAULT_WIDTH - (Client.DEFAULT_WIDTH- MAX_TRACE_LEN)/2 ,CENTER_Y));
		
		for(Beacon b :BeaconList){
			b.draw(drawBeacon);
		}
	}
	
	public void drawL(Graphics2D beacon, Point...points){
		beacon.setColor(new Color(230,230,230));
		if( points.length %2 == 0){
			for(int i =0; i < points.length; ++i){
				beacon.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
				++i;
			}
		}
		repaint();
	}
	
	public void run(){
		
	}
	
	public void create(String choice, int numBeacons, int r){
		WIDTH = getWidth();
		HEIGHT = getHeight();
		int pos=0;
		if(choice.equals("Simple")){
			radius = Client.LINE_SCALE * r;
			offsetX = (Client.DEFAULT_WIDTH - MAX_TRACE_LEN)/2;
			BeaconList.clear();
			
			for (int i=0; i < numBeacons; ++i){
				pos = rand.nextInt(MAX_TRACE_LEN) + offsetX;
				BeaconList.add(new Beacon(pos, CENTER_Y, radius));
			}
		}
		else if (choice.equals("Rigid")){
			
		}
		repaint();
	}
	
	
	public void displayOnLine(List<Beacon> beaconList,int length, int offset){
		//sortX(beaconList);
		int edge = offset;
		for(Beacon b: beaconList){
			int gap =(b.getX() - b.getR()) - edge;
			if(gap > 0){
				int value = b.getX() - gap;
				if( value >= offset && value <=(offset+length)){
					b.setX(value);
				}
			}
		}
	}
	public void sortX(List<Beacon> beacons){
		Collections.sort(beacons, new Comparator<Beacon>(){
			@Override
			public int compare(Beacon b1, Beacon b2){
				return b1.getX() - b2.getX();
			}
		});
	}
}

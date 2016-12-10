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
	private static final long serialVersionUID = 1L;
	protected ArrayList<Beacon> BeaconList;
	protected int radius;
	protected Random rand;
	public final static int MAX_TRACE_LEN = 100 * Client.LINE_SCALE;
	public final static int CENTER_Y = 200;
	public static int WIDTH;
	public static int offsetX;
	protected Algorithm algorithm;
	
	
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
		
		draw(drawBeacon, new Point((Client.DEFAULT_WIDTH - MAX_TRACE_LEN)/2, CENTER_Y), 
			new Point(Client.DEFAULT_WIDTH - (Client.DEFAULT_WIDTH- MAX_TRACE_LEN)/2 ,CENTER_Y));
			
		Color c;
		switch(Client.algChoice){
		case "Simple":
			c = new Color(255,0,0,100);
			break;
		case "Rigid":
			c= new Color(0,255,0,100);
			break;
		case "CustomAlg":
			c = new Color(0,0,255,100);
			break;
			default:
				c = new Color(255,0,0,100);
				break;
		
		}
		for(Beacon b :BeaconList){
			b.draw(drawBeacon,c);
		}
	}
	
	public void draw(Graphics2D beacon, Point p1, Point p2){
		beacon.setColor(new Color(230,230,230));
		beacon.drawLine(p1.x, p1.y, p2.x, p2.y);
		repaint();
	}
	
	public void start(String choice){
		Client.log.info(choice);
		algorithm =  new Algorithm(choice, BeaconList);
		
	}
	
	public void create(String choice, int numBeacons, int r){
		int pos=0;
		radius = Client.LINE_SCALE * r;
		offsetX = (Client.DEFAULT_WIDTH - MAX_TRACE_LEN)/2;
		BeaconList.clear();
		
		for (int i=0; i < numBeacons; ++i){
			pos = rand.nextInt(MAX_TRACE_LEN) + offsetX;
			BeaconList.add(new Beacon(pos, CENTER_Y, radius));
		}
		
		repaint();
	}
	
	
	public void displayOnLine(List<Beacon> beaconList,int length, int offset){
		sortX(beaconList);
		int edge = offset;
		for(Beacon b: beaconList){
			int gap =(b.getX() - b.getR()) - edge;
			if(gap > 0){
				int value = b.getX() - gap;
				if( value >= offset && value <=(offset+length)){
					b.setX(value);
				}
			}
			edge = b.getX() + b.getR();
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

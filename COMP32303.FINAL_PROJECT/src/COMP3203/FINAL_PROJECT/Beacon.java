package COMP3203.FINAL_PROJECT;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;


/**
 * Creates a beacon at point p with radius r
 * @author Mike
 *
 */
public class Beacon {
	protected Point p;
	protected int r;
	
	public Beacon(int x, int r){
		this.p = new Point(x, 100);
		this.r = r;
	}
	
	public int getX(){return p.x;}
	public void setX(int x){p.x = x;}
	public int getR(){return this.r;}
	
	@Override
	public String toString(){
		return "("+p.x+","+p.y+")";
	}
	
	public void draw(Graphics2D circle){
		circle.setStroke(new BasicStroke());
		circle.setColor(Color.red);
		circle.drawLine(p.x-1, p.y, p.x+1, p.y); // sensor node cross-hair
		circle.drawLine(p.x, p.y-1, p.x, p.y+1); // sensor node cross-hair
		circle.setColor(new Color(50, 142, 253, 30));
		circle.fillOval(p.x-r, p.y-r, 2*r, 2*r); // sensor range
	}
	
	
}

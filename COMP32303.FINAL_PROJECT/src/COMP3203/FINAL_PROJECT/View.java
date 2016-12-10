package COMP3203.FINAL_PROJECT;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class View extends JPanel{
	private static final long serialVersionUID = 1L;
	private DataContainer data;
	private Display display;
	
	public View(){
		initLayout();
	}
	
	protected void initLayout(){
		setLayout(new BorderLayout());
		add(data = new DataContainer(), BorderLayout.CENTER);
		add(display = new Display(), BorderLayout.SOUTH);
		
	}
	public DataContainer getData(){
		return data;
	}
	public Display getDisplay(){
		return display;
	}
}

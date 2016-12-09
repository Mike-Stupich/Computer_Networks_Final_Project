package COMP3203.FINAL_PROJECT;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.Panel;

public class test extends Panel{
	List list;
	public test(){
		list = new List();
		//setLayout(new BorderLayout());
		add("Hello", list);
		//add("10", list);
	}
}

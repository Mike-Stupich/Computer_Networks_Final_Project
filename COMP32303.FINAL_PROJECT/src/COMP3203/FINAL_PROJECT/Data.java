package COMP3203.FINAL_PROJECT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
	private static int SUM=0;
	
	//Map between radius and average number of moves
	public static Map<Integer, Double> NumMoves = new HashMap<Integer,Double>();
	
	public static void addToSum(double value){
		SUM+=Math.abs(value);
	}
	
	public static double getSumAtRadius(int r){
		return NumMoves.get(r);
	}
	
	public static void setSumAtRadius(int r, double val){
		NumMoves.put(r, val);
	}

	
	public static void clear(){
		NumMoves.clear();
	}
	

}

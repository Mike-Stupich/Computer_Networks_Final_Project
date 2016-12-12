package COMP3203.FINAL_PROJECT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartData {
	public static double AVERAGE= 0 ;
	public static double SUM = 0;
	public static int SESSION_COUNT = 0;
	public static double SESSION_SUM = 0;
	
	
	public static Map<Integer, List<Double>> R_AVE = new HashMap<Integer, List<Double>>();
	public static Map<Integer, List<Double>> RADIUS_SUM_MAP = new HashMap<Integer, List<Double>>();
	public static Map<Integer, List<Double>> RADIUS_COUNT_MAP = new HashMap<Integer, List<Double>>();
	
	public static void addToSum(double value){
		SUM+=Math.abs(value);
	}
	
	public static void resetSum() {
		// update radius data
		updateMap(RADIUS_COUNT_MAP, 1);
		updateMap(RADIUS_SUM_MAP, SUM);
		updateAve();
		
		// print total movement data
		int length = DataComponent.MAX_TRACE_LEN;
		Client.log.info("Sum of movements = " + SUM + ", (in relation to interval [0,1]: sum/" + length + "=" + 1.0*SUM/length +")");

		// update session data
		SESSION_COUNT++;
		SESSION_SUM += SUM;
		AVERAGE = SESSION_SUM / SESSION_COUNT;
		Client.log.info("Session Average (sum of movements) = " + SESSION_SUM + " / " + SESSION_COUNT + " = " + AVERAGE); 
		
		// reset sum
		SUM = 0;
	}
	
	public static void updateMap(Map<Integer, List<Double>>rMap, double value){
		if(rMap.get(Display.CURRENT_BEACONS) == null){
			List<Double> list = new ArrayList<Double>();
			for(int i =0; i < Display.RADIUS_MAX; ++i){
				list.add(0d);
			}
			list.set(Display.CURRENT_RADIUS, (double)value);
			rMap.put(Display.CURRENT_BEACONS, list);
			
		}
		
		else{
			if(Display.CURRENT_RADIUS< 10){
			List<Double> list = rMap.get(Display.CURRENT_BEACONS);
			list.set(Display.CURRENT_RADIUS, list.get(Display.CURRENT_RADIUS)+value);
			rMap.put(Display.CURRENT_BEACONS, list);
			}
		}
	}
	
	public static void updateAve(){
		int key = Display.CURRENT_BEACONS;
		int r = Display.CURRENT_RADIUS-1;
		
		if(R_AVE.get(key) == null){
			List<Double> list = new ArrayList<Double>();
			for(int i =0; i < Display.RADIUS_MAX; ++i){
				list.add(0d);
			}
				Double count = RADIUS_COUNT_MAP.get(key).get(r);
				Double sum = RADIUS_SUM_MAP.get(key).get(r);
				list.set(r, sum/count);
				R_AVE.put(key, list);
			} 
			else {
				List<Double> list = R_AVE.get(key);
				Double count = RADIUS_COUNT_MAP.get(key).get(r);
				Double sum = RADIUS_SUM_MAP.get(key).get(r);
				list.set(r, sum/count);
				R_AVE.put(key, list);
			}
	}
	
	public static void clearData(){
		R_AVE.clear();
		RADIUS_SUM_MAP.clear();
		RADIUS_COUNT_MAP.clear();
	}
	
}


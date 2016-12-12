package COMP3203.FINAL_PROJECT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CreateGraph extends JFrame{
	private static final long serialVersionUID = 1L;

	public CreateGraph(String appName, String chartName, Map<Integer,List<Double>> map){
		super(appName);
		create(chartName, map);
		pack();
	}
	protected void create(String title, Map<Integer,List<Double>> map){
		XYSeries s = new XYSeries("Num Moves");
		List<Double> l = new ArrayList<Double>();
		
		try{
			for(int i=1; i <= Display.RADIUS_MAX; ++i){
				double ave= 0;
				l.clear();
				l = (List<Double>) map.get(i);
				
				for(int j=0; j < l.size(); ++j){
					ave = l.get(j)/l.size();
					//s.add(i, j);
				}
				s.add(i/100d, ave/DataComponent.MAX_TRACE_LEN);
				
			}
		}
		
		catch(NullPointerException e){
			
		}
		XYSeriesCollection dataset =  new XYSeriesCollection();
		dataset.addSeries(s);
		NumberAxis x = new NumberAxis("Radius");
		NumberAxis y = new NumberAxis("Number of movements");
		XYSplineRenderer r = new XYSplineRenderer(3);
		XYPlot plot = new XYPlot(dataset, x, y, r);
		JFreeChart chart = new JFreeChart(plot);
		chart.setTitle(title);
		ChartPanel panel = new ChartPanel(chart);
		
		add(panel);
	}

	
}

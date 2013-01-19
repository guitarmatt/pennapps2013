package pennapps2013.where2meet.client;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter {
}

/*public class Plotter extends JPanel {
	private static final long serialVersionUID = -1928984286891999727L;

	public Plotter() {
		super();
		
		LatLng[] points = new LatLng[] {
			new LatLng(-25.323223, 50.23232323),
			new LatLng(0         , 80.4329433),
			new LatLng(30.5483432, 78.3928322),
			new LatLng(14.4324334, 65.3829849),
			new LatLng(21.6569860, 60.4383493),
			new LatLng(42.4343334, 100.7232322)
		};
		
		Circle circle = SEC.findCenter(points);
		
		System.out.println(circle);
		
		XYSeries centerSeries = new XYSeries("Center");
		centerSeries.add(circle.centerX, circle.centerY);
		
		XYSeries pointSeries = new XYSeries("Locations");
		for (int i = 0; i < points.length; i++)
			points[i].addTo(pointSeries);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(pointSeries);
		dataset.addSeries(centerSeries);
		
		XYDotRenderer r = new XYDotRenderer();
		r.setDotHeight(5);
		r.setDotWidth(5);
		XYPlot plot = new XYPlot(dataset, new NumberAxis("X"), new NumberAxis("Y"), r);
		XYShapeAnnotation area = new XYShapeAnnotation(
				new Ellipse2D.Double(circle.centerX - circle.radius, circle.centerY - circle.radius,
						2 * circle.radius, 2 * circle.radius),
				new BasicStroke(1.0f),
				Color.BLACK);
		
		plot.addAnnotation(area);
		
		NumberAxis xAxis = (NumberAxis)plot.getDomainAxis();
		NumberAxis yAxis = (NumberAxis)plot.getRangeAxis();
		xAxis.setRange(-150, 150);
		xAxis.setTickUnit(new NumberTickUnit(15));
		yAxis.setRange(-150, 150);
		yAxis.setTickUnit(new NumberTickUnit(15));

		JFreeChart chart = new JFreeChart(plot);
		ChartPanel chartPanel = new ChartPanel(chart);

		add(chartPanel);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Where2Meet");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new Plotter(), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}*/

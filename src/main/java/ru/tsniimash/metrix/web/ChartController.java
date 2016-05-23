package ru.tsniimash.metrix.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/charts")
public class ChartController
{
	private final Logger logger = Logger.getLogger(ChartController.class);
	
	@RequestMapping(value="/testpie", method = RequestMethod.GET)
	public void drawPieChart(HttpServletResponse httpServletResponse)
	{
		httpServletResponse.setContentType("image/png");
		PieDataset dataset = createDataset();
		
		JFreeChart chart = createChart(dataset, "Test Pie");
		
		try
		{
			ChartUtilities.writeChartAsPNG(httpServletResponse.getOutputStream(), chart, 750, 400);
			httpServletResponse.getOutputStream().close();
		}
		catch (Exception exception)
		{
			logger.log(Level.ERROR, exception);
		}
	}
	
	private PieDataset createDataset()
	{
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Mac", 21);
		dataset.setValue("Linux", 30);
		dataset.setValue("Window", 40);
		dataset.setValue("Others", 9);
		return dataset;
	}
	
	private JFreeChart createChart(PieDataset dataset, String chartTitle)
	{
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset, true, true, false);
		PiePlot3D piePlot3D = PiePlot3D.class.cast(chart.getPlot());
		piePlot3D.setStartAngle(290);
		piePlot3D.setDirection(Rotation.CLOCKWISE);
		piePlot3D.setForegroundAlpha(0.5f);
		return chart;
	}

}

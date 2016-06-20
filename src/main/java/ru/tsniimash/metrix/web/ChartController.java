package ru.tsniimash.metrix.web;

import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.tsniimash.metrix.pojo.ProjectVectorModule;
import ru.tsniimash.metrix.service.GraphDataLoader;

@Controller
@RequestMapping("/charts")
public class ChartController
{
	private final Logger logger = Logger.getLogger(ChartController.class);
	
	@Resource
	private GraphDataLoader graphDataLoader;
	
	@RequestMapping(value="/pie/{num}", method = RequestMethod.GET)
	public void drawPieChart(HttpServletResponse httpServletResponse, @PathVariable ("num") int clusterNum)
	{
		httpServletResponse.setContentType("image/png");
		PieDataset dataset = createDataset(clusterNum);
		
		String label = clusterNum==1?"Первоприоритетные проекты":"Второстепенные проекты";
		JFreeChart chart = createChart(dataset, label);
		
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
	
	private PieDataset createDataset(int clusterNum)
	{
		List<ProjectVectorModule> cluster = clusterNum==1?graphDataLoader.getProjectClusters().getCluster1():graphDataLoader.getProjectClusters().getCluster2();
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		cluster.stream().forEach(x -> dataset.setValue(x.getProject().getProject_id()+" ("+x.getProject().getName()+")", x.getGradeMod()));
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

package neural_net;

import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;


public class Visualization {

       // Run As > Java Application 으로 실행하면 바로 확인할 수 있음.
	static ChartFrame frame1;
    /*public static void main(final String[] args) {
       Visualization demo = new Visualization();
             JFreeChart chart = demo.getChart();
             frame1=new ChartFrame("Weight Change Graph",chart);
             frame1.setSize(800,500); 
             frame1.getChartPanel().setLayout(null);
             frame1.setVisible(true);
    }*/
    
    public ChartFrame returnChart() {
		return frame1;
    }
    public static double[][] wIH;
    public static double[][] wHO;
    DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1
    DefaultCategoryDataset dataset12 = new DefaultCategoryDataset();         // bar chart 2
    DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();                // line chart 1
    public JFreeChart getChart() {
        wIH = Neuron.getWIH();
        wHO = Neuron.getWHO();
        
        dataset1.addValue(wIH[0][0], "Father's weight", "w11");
        dataset1.addValue(wIH[0][1], "Father's weight", "w12");
        dataset1.addValue(wIH[0][2], "Father's weight", "w13");
        dataset1.addValue(wIH[0][3], "Father's weight", "w14");
        dataset1.addValue(wIH[0][4], "Father's weight", "w15");
        
        dataset1.addValue(wIH[1][0], "Mother's weight", "w21");
        dataset1.addValue(wIH[1][1], "Mother's weight", "w22");
        dataset1.addValue(wIH[1][2], "Mother's weight", "w23");
        dataset1.addValue(wIH[1][3], "Mother's weight", "w24");
        dataset1.addValue(wIH[1][4], "Mother's weight", "w25");
        
        dataset1.addValue(wHO[0][0], "My weight", "w31");
        dataset1.addValue(wHO[1][0], "My weight", "w32");
        dataset1.addValue(wHO[2][0], "My weight",  "w33");
        dataset1.addValue(wHO[3][0], "My weight",  "w34");
        dataset1.addValue(wHO[4][0],  "My weight",  "w35");


        // Create Rendering
        final BarRenderer renderer = new BarRenderer();

        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();

        final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        Font f = new Font("210 맨발의청춘L", Font.BOLD, 14);
        Font axisF = new Font("210 맨발의청춘L", Font.BOLD, 14);
      
        // Rendering setting
        // Graph1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
       // renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);
        renderer.setSeriesPaint(0, new Color(183,240,177));


        // Create plot
        final CategoryPlot plot = new CategoryPlot();

        // allcate data 
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        // plot basic setting
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);   
        plot.setDomainGridlinesVisible(true);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        plot.setDomainAxis(new CategoryAxis());   
        plot.getDomainAxis().setTickLabelFont(axisF); 
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // coordinate category label position
        plot.setRangeAxis(new NumberAxis());        
        plot.getRangeAxis().setTickLabelFont(axisF);  

        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        return chart;
    }
}
package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;


/**
 * 柱状图基类
 *
 * @author mln-wyf
 */
public abstract class StackedBarChartAction {
    public JFreeChart chart;
    private String title; // 标题
    private String x; // 横坐标名称
    private String y; // 纵坐标名称
    private String y2;// 纵坐标右边名称

    /**
     * 双Y轴折线图
     *
     * @param datasetLeft  Y轴左边的数据集
     * @param datasetRight Y轴右边的数据集
     * @return
     */
    protected JFreeChart createLineChart(XYDataset datasetLeft, XYDataset datasetRight) {
        // 设置字体样式
        Font fs = new Font("微软雅黑", Font.BOLD, 12);
        Font f = new Font("微软雅黑", Font.PLAIN, 12);


        chart = ChartFactory.createXYLineChart(title,
                x,
                y,
                datasetLeft,
                PlotOrientation.VERTICAL,
                true,
                true,
                true);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setDomainPannable(true);
        xyplot.setRangePannable(true);

        // 设置X轴显示方式
        //NumberAxis domainAxis = new NumberAxis(this.x);
        NumberAxis domainAxis = (NumberAxis) xyplot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //设置x轴数字为整数 
        domainAxis.setVisible(true);
        domainAxis.setAutoRangeIncludesZero(false);
        xyplot.setRangeAxis(0, domainAxis);

        // 左边y轴显示方式
        //NumberAxis numberaxis= (NumberAxis)plotxy.getRangeAxis();//y轴整数显示 
        NumberAxis numberaxis = new NumberAxis(this.y);
        numberaxis.setLabelFont(fs);
        numberaxis.setTickLabelFont(f);
        xyplot.setRangeAxis(0, numberaxis);

        // 右边y轴显示方式
        NumberAxis numberaxis2 = new NumberAxis(this.y2);
        numberaxis2.setLabelFont(fs);
        numberaxis2.setTickLabelFont(f);
        xyplot.setRangeAxis(1, numberaxis2);
        xyplot.setDataset(1, datasetRight);
        xyplot.mapDatasetToRangeAxis(1, 1);

        // 左边y轴
        XYItemRenderer renderer = xyplot.getRenderer();
        //xylineandshaperenderer1.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());   //数据显示格式
        if (renderer instanceof XYLineAndShapeRenderer)  //显示数据
        {
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) renderer;
            xylineandshaperenderer.setBaseShapesVisible(true);
            xylineandshaperenderer.setBaseShapesFilled(true);
        }
        // 右边y轴
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        renderer1.setBaseShapesVisible(true);
        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator());  //数据显示格式
        renderer1.setSeriesPaint(0, Color.green);
        renderer1.setOutlinePaint(Color.green);
        renderer1.setUseOutlinePaint(true);
        renderer1.setUseFillPaint(true);
        renderer1.setDrawOutlines(true);
        xyplot.setRenderer(1, renderer1);

        xyplot.setBackgroundPaint(new Color(238, 244, 255));//设置图表的颜色        
        xyplot.setDomainGridlinePaint(Color.lightGray);// 设置垂直网格线的颜色
        xyplot.setRangeGridlinePaint(Color.lightGray);// 设置水平网格线的颜色
        xyplot.setDomainGridlinesVisible(true); // 设置垂直网格线是否显示
        xyplot.setRangeGridlinesVisible(true); // 设置水平网格线是否显示

        return chart;
    }

    private static XYDataset createDataset1() {
        XYSeries xyseries = new XYSeries("Random   Data   1 ");
        xyseries.add(1.0D, 181.80000000000001D);
        xyseries.add(2D, 167.30000000000001D);
        xyseries.add(3D, 153.80000000000001D);
        xyseries.add(4D, 167.59999999999999D);
        xyseries.add(5D, 158.80000000000001D);
        xyseries.add(6D, 148.30000000000001D);
        xyseries.add(7D, 153.90000000000001D);
        xyseries.add(8D, 142.69999999999999D);
        xyseries.add(9D, 123.2D);
        xyseries.add(10D, 131.80000000000001D);
        xyseries.add(11D, 139.59999999999999D);
        xyseries.add(12D, 142.90000000000001D);
        xyseries.add(13D, 138.69999999999999D);
        xyseries.add(14D, 137.30000000000001D);
        xyseries.add(15D, 143.90000000000001D);
        xyseries.add(16D, 139.80000000000001D);
        xyseries.add(17D, 137D);
        xyseries.add(18D, 132.80000000000001D);
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyseries);
        return xyseriescollection;
    }

    private static XYDataset createDataset2() {
        XYSeries xyseries = new XYSeries("Random   Data   2 ");
        xyseries.add(1.0D, 429.60000000000002D);
        xyseries.add(2D, 323.19999999999999D);
        xyseries.add(3D, 417.19999999999999D);
        xyseries.add(4D, 624.10000000000002D);
        xyseries.add(5D, 422.60000000000002D);
        xyseries.add(6D, 619.20000000000005D);
        xyseries.add(7D, 416.5D);
        xyseries.add(8D, 512.70000000000005D);
        xyseries.add(9D, 501.5D);
        xyseries.add(10D, 306.10000000000002D);
        xyseries.add(11D, 410.30000000000001D);
        xyseries.add(12D, 511.69999999999999D);
        xyseries.add(13D, 611D);
        xyseries.add(14D, 709.60000000000002D);
        xyseries.add(15D, 613.20000000000005D);
        xyseries.add(16D, 711.60000000000002D);
        xyseries.add(17D, 708.79999999999995D);
        xyseries.add(18D, 501.60000000000002D);
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyseries);
        return xyseriescollection;
    }
    public static void main(String[] args) {
        StackedBarChartAction action = new StackedBarChartAction() {
            @Override
            protected JFreeChart createLineChart(XYDataset datasetLeft, XYDataset datasetRight) {
                return super.createLineChart(StackedBarChartAction.createDataset1(), StackedBarChartAction.createDataset1());
            }
        };
        action.createLineChart(StackedBarChartAction.createDataset1(),StackedBarChartAction.createDataset2());
    }
}

 
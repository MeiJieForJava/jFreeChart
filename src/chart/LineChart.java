package chart;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.ui.RectangleEdge;
import util.ChartUtils;
import util.Serie;

/**
 * @author ccw
 * @date 2014-6-11
 * <p>
 * 创建图表步骤：<br/>
 * 1：创建数据集合<br/>
 * 2：创建Chart：<br/>
 * 3:设置抗锯齿，防止字体显示不清楚<br/>
 * 4:对柱子进行渲染，<br/>
 * 5:对其他部分进行渲染<br/>
 * 6:使用chartPanel接收<br/>
 * <p>
 * </p>
 */
public class LineChart {
    public LineChart() {
    }

//    public DefaultCategoryDataset createDataset() {
//        // 标注类别   x轴名称
//        String[] categories = {"2017/11/3", "2017/11/4", "2017/11/5", "2017/11/6", "2017/11/7", "2017/11/8", "2017/11/9"};
//        Vector<Serie> series = new Vector<Serie>();
//        // 柱子名称：柱子所有的值集合
////        series.add(new Serie("Tokyo", new Double[]{49900D, 71500D, 106400D, 129200D, 144000D, 176000D, 135600D}));
////        series.add(new Serie("New York", new Double[]{83600D, 78800D, 98500D, 93400D, 106000D, 84500D, 105000D, 104300D, 91200D, 83500D, 106600D, 92300D}));
//        series.add(new Serie("Tokyo", new Double[]{49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6}));
//        series.add(new Serie("New York", new Double[]{83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0}));
//        // 1：创建数据集合
//        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
//        return dataset;
//    }

    public ChartPanel createChart() {


        String[] categories = {"2017/11/3", "2017/11/4", "2017/11/5", "2017/11/6", "2017/11/7", "2017/11/8", "2017/11/9"};
        Vector<Serie> series1 = new Vector<Serie>();
        series1.add(new Serie("Tokyo", new Double[]{49900D, 71500D, 106400D, 129200D, 144000D, 176000D, 135600D}));
        DefaultCategoryDataset dataset1 = ChartUtils.createDefaultCategoryDataset(series1, categories);
        Vector<Serie> series2 = new Vector<Serie>();
        series2.add(new Serie("New York", new Double[]{83600D, 78800D, 98500D, 93400D, 106000D, 84500D, 105000D}));
        DefaultCategoryDataset dataset2 = ChartUtils.createDefaultCategoryDataset(series2, categories);
        // 2：创建Chart[创建不同图形]
        JFreeChart chart = ChartFactory.createLineChart("Monthly Average Rainfall", "", "first Y", dataset1);
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[[采用不同渲染]]
        ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//数字和坐标点
        // 5:对其他部分进行渲染
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();

        categoryPlot.setDataset(1, dataset2);
        categoryPlot.mapDatasetToRangeAxis(1, 1);
        // X轴刻度
        CategoryAxis categoryaxis = categoryPlot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // 右侧Y轴
        NumberAxis numberaxis = new NumberAxis("second Y");
        categoryPlot.setRangeAxis(1, numberaxis);
        // 隐藏Y刻度
        numberaxis.setAxisLineVisible(false);
        numberaxis.setTickMarksVisible(false);

        // 设置折线图样式
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
        lineRenderer.setBaseShapesVisible(true);// 数据点绘制形状
        categoryPlot.setRenderer(1, lineRenderer);

        categoryPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// 绘制Z-index, 将折线图在前面
        chart.getLegend().setPosition(RectangleEdge.TOP);//标注在顶部
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));


        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 420);
        frame.setLocationRelativeTo(null);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建图形
                ChartPanel chartPanel = new LineChart().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

}

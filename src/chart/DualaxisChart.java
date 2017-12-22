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
 * 2个Y轴图形<br/>
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

public class DualaxisChart {
    public DualaxisChart() {
    }

    public ChartPanel createChart() {
        String[] categories = {"1999-12-31", "2000-12-31", "2001-12-31", "2002-12-31", "2003-12-31"};
//        for (int i = 0; i < categories.length; i++) {
//            categories[i] = categories[i].substring(0, 4);
//        }
        Vector<Serie> seriesNetProfit = new Vector<Serie>();
        // 净利润
        Object[] netProfit = {1725988.60, 2136652.91, 1431268.27, 1942454.54, 1320030.03};
        // 股利支付率
        Object[] payoutRatio = {1147963.38, 1326830.91, 1053184.13, 1179693.29, 932461.91};
        seriesNetProfit.add(new Serie("净利润", netProfit));

        Vector<Serie> seriesPayoutRatio = new Vector<Serie>();
        seriesPayoutRatio.add(new Serie("股利支付率", payoutRatio));

        DefaultCategoryDataset datasetNetProfit = ChartUtils.createDefaultCategoryDataset(seriesNetProfit, categories);
        JFreeChart chart = ChartFactory.createLineChart("", "", "净利润(万元)", datasetNetProfit);
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//数字和坐标点
//		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);
        // 设置坐标轴
        ChartUtils.setXAixs(chart.getCategoryPlot());
        ChartUtils.setYAixs(chart.getCategoryPlot());
        // linechart
        CategoryPlot categoryplot = chart.getCategoryPlot();
        DefaultCategoryDataset datasetPayoutRatio = ChartUtils.createDefaultCategoryDataset(seriesPayoutRatio, categories);
        categoryplot.setDataset(1, datasetPayoutRatio);
        categoryplot.mapDatasetToRangeAxis(1, 1);
        // X轴刻度
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        // 右侧Y轴
        NumberAxis numberaxis = new NumberAxis("股利支付率(%)");
        categoryplot.setRangeAxis(1, numberaxis);
        // 隐藏Y刻度
        numberaxis.setAxisLineVisible(false);
        numberaxis.setTickMarksVisible(false);

        // 设置折线图样式
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
        lineRenderer.setBaseShapesVisible(true);// 数据点绘制形状
        categoryplot.setRenderer(1, lineRenderer);

        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// 绘制Z-index, 将折线图在前面
//        chart.getLegend().setPosition(RectangleEdge.TOP);//标注在顶部
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
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
                ChartPanel chartPanel = new DualaxisChart().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

}

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
 * 2��Y��ͼ��<br/>
 * ����ͼ���裺<br/>
 * 1���������ݼ���<br/>
 * 2������Chart��<br/>
 * 3:���ÿ���ݣ���ֹ������ʾ�����<br/>
 * 4:�����ӽ�����Ⱦ��<br/>
 * 5:���������ֽ�����Ⱦ<br/>
 * 6:ʹ��chartPanel����<br/>
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
        // ������
        Object[] netProfit = {1725988.60, 2136652.91, 1431268.27, 1942454.54, 1320030.03};
        // ����֧����
        Object[] payoutRatio = {1147963.38, 1326830.91, 1053184.13, 1179693.29, 932461.91};
        seriesNetProfit.add(new Serie("������", netProfit));

        Vector<Serie> seriesPayoutRatio = new Vector<Serie>();
        seriesPayoutRatio.add(new Serie("����֧����", payoutRatio));

        DefaultCategoryDataset datasetNetProfit = ChartUtils.createDefaultCategoryDataset(seriesNetProfit, categories);
        JFreeChart chart = ChartFactory.createLineChart("", "", "������(��Ԫ)", datasetNetProfit);
        ChartUtils.setAntiAlias(chart);// �����
        ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//���ֺ������
//		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);
        // ����������
        ChartUtils.setXAixs(chart.getCategoryPlot());
        ChartUtils.setYAixs(chart.getCategoryPlot());
        // linechart
        CategoryPlot categoryplot = chart.getCategoryPlot();
        DefaultCategoryDataset datasetPayoutRatio = ChartUtils.createDefaultCategoryDataset(seriesPayoutRatio, categories);
        categoryplot.setDataset(1, datasetPayoutRatio);
        categoryplot.mapDatasetToRangeAxis(1, 1);
        // X��̶�
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        // �Ҳ�Y��
        NumberAxis numberaxis = new NumberAxis("����֧����(%)");
        categoryplot.setRangeAxis(1, numberaxis);
        // ����Y�̶�
        numberaxis.setAxisLineVisible(false);
        numberaxis.setTickMarksVisible(false);

        // ��������ͼ��ʽ
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
        lineRenderer.setBaseShapesVisible(true);// ���ݵ������״
        categoryplot.setRenderer(1, lineRenderer);

        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// ����Z-index, ������ͼ��ǰ��
//        chart.getLegend().setPosition(RectangleEdge.TOP);//��ע�ڶ���
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
                // ����ͼ��
                ChartPanel chartPanel = new DualaxisChart().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

}

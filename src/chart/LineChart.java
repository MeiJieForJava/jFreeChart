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
public class LineChart {
    public LineChart() {
    }

//    public DefaultCategoryDataset createDataset() {
//        // ��ע���   x������
//        String[] categories = {"2017/11/3", "2017/11/4", "2017/11/5", "2017/11/6", "2017/11/7", "2017/11/8", "2017/11/9"};
//        Vector<Serie> series = new Vector<Serie>();
//        // �������ƣ��������е�ֵ����
////        series.add(new Serie("Tokyo", new Double[]{49900D, 71500D, 106400D, 129200D, 144000D, 176000D, 135600D}));
////        series.add(new Serie("New York", new Double[]{83600D, 78800D, 98500D, 93400D, 106000D, 84500D, 105000D, 104300D, 91200D, 83500D, 106600D, 92300D}));
//        series.add(new Serie("Tokyo", new Double[]{49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6}));
//        series.add(new Serie("New York", new Double[]{83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0}));
//        // 1���������ݼ���
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
        // 2������Chart[������ͬͼ��]
        JFreeChart chart = ChartFactory.createLineChart("Monthly Average Rainfall", "", "first Y", dataset1);
        // 3:���ÿ���ݣ���ֹ������ʾ�����
        ChartUtils.setAntiAlias(chart);// �����
        // 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
        ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//���ֺ������
        // 5:���������ֽ�����Ⱦ
        ChartUtils.setXAixs(chart.getCategoryPlot());// X��������Ⱦ
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y��������Ⱦ
        // ���ñ�ע�ޱ߿�
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();

        categoryPlot.setDataset(1, dataset2);
        categoryPlot.mapDatasetToRangeAxis(1, 1);
        // X��̶�
        CategoryAxis categoryaxis = categoryPlot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // �Ҳ�Y��
        NumberAxis numberaxis = new NumberAxis("second Y");
        categoryPlot.setRangeAxis(1, numberaxis);
        // ����Y�̶�
        numberaxis.setAxisLineVisible(false);
        numberaxis.setTickMarksVisible(false);

        // ��������ͼ��ʽ
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
        lineRenderer.setBaseShapesVisible(true);// ���ݵ������״
        categoryPlot.setRenderer(1, lineRenderer);

        categoryPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);// ����Z-index, ������ͼ��ǰ��
        chart.getLegend().setPosition(RectangleEdge.TOP);//��ע�ڶ���
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));


        // 6:ʹ��chartPanel����
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
                ChartPanel chartPanel = new LineChart().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

}

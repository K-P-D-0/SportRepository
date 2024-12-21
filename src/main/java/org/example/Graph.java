package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serial;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

public class Graph extends ApplicationFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    public Graph(String title, Map<String, Double> value) {
        super(title);
        setContentPane(createDemoPanel(value));
    }

    public JPanel createDemoPanel(Map<String, Double> value) {
        CategoryDataset dataset = createDataset(value);
        JFreeChart chart = createChart(dataset);
        chart.setPadding(new RectangleInsets(4, 4, 2, 2));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        return chartPanel;
    }

    private CategoryDataset createDataset(Map<String, Double> value) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (var key: value.keySet())
            dataset.addValue(value.get(key), key, "");
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Количество объектов спорта по областям",
                null,
                "Количество объектов спорта",
                dataset);
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }
}
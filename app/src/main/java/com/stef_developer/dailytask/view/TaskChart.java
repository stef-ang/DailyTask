package com.stef_developer.dailytask.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;

import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by tsaqova on 5/23/15.
 */
public class TaskChart extends View {

    public TaskChart(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        String[] code = new String[] {
                "Completed Task", "Uncompleted Task", "Failed Task"
        };

        // Pie Chart Section Value
        double[] distribution = { 58, 26, 16 } ;

        // Color of each Pie Chart Sections
        int[] colors = { Color.GREEN, Color.YELLOW, Color.RED };

        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Percentages completed Task :");
        for(int i=0 ;i < distribution.length;i++){
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer  = new DefaultRenderer();
        for(int i = 0 ;i<distribution.length;i++){
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setChartTitle("Percentages completed Task :");
        defaultRenderer.setZoomButtonsVisible(false);
        defaultRenderer.setDisplayValues(false);
        defaultRenderer.setZoomEnabled(false);
        defaultRenderer.setExternalZoomEnabled(false);
        defaultRenderer.setShowLabels(false);
        defaultRenderer.setShowLegend(false);
        defaultRenderer.setLabelsColor(Color.BLACK);
        defaultRenderer.setLabelsTextSize(18);
        PieChart pieChart = new PieChart(distributionSeries, defaultRenderer);
        pieChart.setCenterX(150);
        Paint paint = new Paint();
        pieChart.draw(canvas, 0, 0, 400, 350, paint);
    }

}

package com.fanneng.common.customview.chart.line;


import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;

import java.util.List;

/**
 * describe：线图表基类
 *
 * @author ：鲁宇峰 on 2018/5/23 16：20
 *         email：466708987@qq.com
 */

public abstract class BaseLineChart<T extends Entry> {

    protected BarLineChartBase mChart;
    protected List<T>[] mEntries;
    protected String[] labels;
    protected int[] mChartColors;
    protected float mTextSize;
    protected float mLeftTextSize;
    protected int mValueColor;

    /**
     * 为true表示需要设置成虚线
     */
    protected boolean[] hasDotted;


    /**
     * @param chart        图表布局
     * @param entries      数据源
     * @param labels       标签
     * @param chartColor   线条颜色
     * @param valueColor   轴颜色
     * @param textSize     X轴字体大小
     * @param leftTextSize Y轴字体大小
     * @param hasDotted    是否是虚线
     */
    protected BaseLineChart(BarLineChartBase chart, List<T>[] entries, String[] labels,
                            int[] chartColor, int valueColor, float textSize, float leftTextSize, boolean[] hasDotted) {
        this.mChart = chart;
        this.mEntries = entries;
        this.labels = labels;
        this.mValueColor = valueColor;
        this.mChartColors = chartColor;
        this.mTextSize = textSize;
        this.mLeftTextSize = leftTextSize;
        this.hasDotted = hasDotted;
        initChart();
    }

    /**
     * <p>初始化chart</p>
     */
    protected void initChart() {
        initProperties();

        setChartData();

        initLegend(Legend.LegendForm.LINE, mTextSize, mValueColor);

        initXAxis(mValueColor, mTextSize);

        initLeftAxis(mValueColor, mLeftTextSize);


    }


    private void initLeftAxis(int color, float textSize) {
        YAxis leftAxis = mChart.getAxisLeft();

        leftAxis.setTextColor(color);
        leftAxis.setTextSize(textSize);
        float yMax = mChart.getData().getYMax() == 0 ? 100f : mChart.getData().getYMax();
        leftAxis.setAxisMaximum(yMax + yMax * 0.007f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(false);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setLabelCount(6);
        leftAxis.setAxisLineWidth(1f);
        leftAxis.setAxisLineColor(mValueColor);

        mChart.getAxisRight().setEnabled(false);

    }

    private void initXAxis(int color, float textSize) {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(textSize);
        xAxis.setAxisMinimum(0);
        xAxis.setTextColor(color);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        xAxis.setAxisLineWidth(1f);
        xAxis.setLabelCount(8);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setAxisLineColor(mValueColor);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisMinimum(mChart.getData().getXMin());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


    }

    /**
     * <p>初始化属性信息</p>
     */
    private void initProperties() {
        mChart.setNoDataText("");
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleXEnabled(true);
        mChart.setPinchZoom(false);
        mChart.setVisibleXRangeMaximum(6);
        mChart.setScaleYEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);
    }

    /**
     * <p>初始化Legend展示信息</p>
     *
     * @param form           样式
     * @param legendTextSize 文字大小
     * @param legendColor    颜色值
     */
    public void initLegend(Legend.LegendForm form, float legendTextSize, int legendColor) {
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(form);

        l.setTextSize(legendTextSize);
        l.setTextColor(legendColor);

        updateLegendOrientation(Legend.LegendVerticalAlignment.BOTTOM, Legend.LegendHorizontalAlignment.RIGHT, Legend
                .LegendOrientation.HORIZONTAL);
    }

    /**
     * <p>图例说明</p>
     *
     * @param vertical    垂直方向位置 默认底部
     * @param horizontal  水平方向位置 默认右边
     * @param orientation 显示方向 默认水平展示
     */

    public void updateLegendOrientation(Legend.LegendVerticalAlignment vertical, Legend.LegendHorizontalAlignment
            horizontal, Legend.LegendOrientation orientation) {
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(vertical);
        l.setHorizontalAlignment(horizontal);
        l.setOrientation(orientation);
        l.setDrawInside(false);

    }

    /**
     * 图表value显示开关
     */
    public void toggleChartValue() {
        List<BaseDataSet> sets = mChart.getData().getDataSets();
        for (BaseDataSet iSet : sets) {
            iSet.setDrawValues(!iSet.isDrawValuesEnabled());
        }
        mChart.invalidate();
    }

    public void setMarkView(MarkerView markView) {
        // For bounds control
        markView.setChartView(mChart);
        // Set the marker to the chart
        mChart.setMarker(markView);
        mChart.invalidate();
    }

    /**
     * x/ylabel显示样式
     *
     * @param xvalueFromatter    x
     * @param leftValueFromatter y
     */
    public void setAxisFormatter(IAxisValueFormatter xvalueFromatter, IAxisValueFormatter leftValueFromatter) {
        mChart.getXAxis().setValueFormatter(xvalueFromatter);
        mChart.getAxisLeft().setValueFormatter(leftValueFromatter);
        mChart.invalidate();

    }

    protected abstract void setChartData();


    /**
     * value显示格式设置
     *
     * @param valueFormatter IValueFormatter
     */
    public void setDataValueFormatter(IValueFormatter valueFormatter) {
        mChart.getData().setValueFormatter(valueFormatter);
    }
}

package com.why.ycharts.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.why.ycharts.chart.LineChart;
import com.why.ycharts.data.LineChartData;

import java.util.ArrayList;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public class LineChartView extends ChartView<LineChart>{

	private static final String TAG = "LineChartView";
	public LineChartView(Context context) {
		super(context);
	}

	public LineChartView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void init() {

		LineChart lineChart = new LineChart();
		ArrayList category = new ArrayList();
		for (int i = 0; i < 6; i++) {
			category.add(i);
		}
		lineChart.getXAxis().setData(category);
		lineChart.getYAxis().setData(category);
		lineChart.getBorder().setShow(false);
		addChart(lineChart);
	}

	public void addChartData(LineChartData data){
		LineChart chart = getChart();
		if (chart!=null){
			chart.addChartData(data);
		}else {
			Log.w(TAG, "addChartData failed, chart is null ");
		}
	}

}

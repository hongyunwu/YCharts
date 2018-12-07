package com.why.ycharts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.why.ycharts.chart.Chart;
import com.why.ycharts.chart.axis.DataAxis;
import com.why.ycharts.contract.IChart;
import com.why.ycharts.data.ChartData;
import com.why.ycharts.data.LineChartData;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public class ChartView<T extends Chart> extends View {
	private T mChart;

	public ChartView(Context context) {
		this(context, null);
	}

	public ChartView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	protected void init() {

	}

	@Override
	protected void onDraw(Canvas canvas) {
		IChart chart = getChart();
		if (chart != null){
			chart.onDraw(canvas);

		}
	}

	public T getChart() {
		return this.mChart;
	}

	protected void addChart(T chart) {
		this.mChart = chart;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		T chart = getChart();
		if (chart != null) {
			chart.setChartRange(w, h, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
		}
	}


}

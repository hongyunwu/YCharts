package com.why.ycharts.chart;

import android.graphics.Canvas;

import com.why.ycharts.chart.axis.Axis;
import com.why.ycharts.chart.axis.DataAxis;
import com.why.ycharts.data.ChartData;
import com.why.ycharts.data.LineChartData;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public class LineChart extends Chart<LineChartData> {


	private DataAxis mCategoryAxis;
	private DataAxis mDataAxis;

	@Override
	public ChartType getChartType() {
		return ChartType.LINE;
	}

	@Override
	public void render(Canvas canvas) {
		//轴线
		DataAxis xAxis = getXAxis();
		xAxis.setRange(0,0,mHeight - mTranslateH,mWidth - mTranslateW);
		xAxis.render(canvas);
		DataAxis yAxis = getYAxis();
		yAxis.setRange(0,0,mHeight - mTranslateH,mWidth - mTranslateW);
		yAxis.render(canvas);
		//数据
		for (LineChartData data : mData){
			renderData(data,canvas);
		}
	}

	@Override
	public void renderData(LineChartData data, Canvas canvas) {


	}


	public DataAxis getXAxis() {
		if (mCategoryAxis==null){
			mCategoryAxis = new DataAxis<String>();
			mCategoryAxis.setType(DataAxis.Type.CATEGORY);
		}
		return mCategoryAxis;
	}

	public DataAxis getYAxis() {
		if (mDataAxis==null){
			mDataAxis = new DataAxis<String>();
			mDataAxis.setType(DataAxis.Type.VALUE);
		}
		return mDataAxis;
	}

}

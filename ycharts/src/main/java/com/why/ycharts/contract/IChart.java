package com.why.ycharts.contract;

import android.graphics.Canvas;

import com.why.ycharts.data.ChartData;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public interface IChart<T extends ChartData>{

	void onDraw(Canvas canvas);


	ChartType getChartType();

	void addChartData(T data);

	enum ChartType{
		NONE,LINE,PIE;
	}
	void setChartRange(int w, int h, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom);

}

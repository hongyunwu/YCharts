package com.why.ycharts.chart.axis;


import android.graphics.Canvas;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public class DataAxis<T> extends Axis<T>{

	//默认位置
	int mPosition = Position.BOTTOM;

	int mEdgeDistance = 10;

	@Override
	public void render(Canvas canvas) {



	}

	public void setRange(int x, int y, int height, int width) {
	}

	public static class  Position{
		public static final int TOP = 0;
		public static final int BOTTOM = 1;
		public static final int LEFT = 2;
		public static final int RIGHT = 3;
	}

	public static class Type extends Axis.Type{
		public static final int VALUE = 0;
		public static final int CATEGORY = 1;
		public static final int TIME = 2;
		public static final int LOG = 3;
	}



}

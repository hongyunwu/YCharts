package com.why.ycharts.chart;

import android.graphics.Canvas;
import android.util.Log;

import com.why.ycharts.chart.border.LineBorder;
import com.why.ycharts.contract.IBorder;
import com.why.ycharts.contract.IChart;
import com.why.ycharts.data.ChartData;

import java.util.ArrayList;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public abstract class Chart<T extends ChartData> implements IChart<T> {
	private static final String TAG = "Chart";
	ArrayList<T> mData = new ArrayList<>();

	private LineBorder mLineBorder;
	protected int mWidth;
	protected int mHeight;
	protected int mTranslateW;
	protected int mTranslateH;

	public Chart() {
		initChart();
	}

	protected void initChart() {

	}

	@Override
	public void addChartData(T data) {
		if (data!=null){
			this.mData.add(data);
		}else {
			Log.d(TAG, "addChartData fail:data is null");
		}
	}

	@Override
	public final void onDraw(Canvas canvas) {
		//平移
		canvas.save();
		canvas.translate(mTranslateW,mTranslateH);
		render(canvas);
		//边框
		IBorder border = getBorder();
		if (border.isShow()){

			border.render(canvas);
		}
		canvas.restore();
	}

	public abstract void renderData(T data, Canvas canvas);

	protected abstract void render(Canvas canvas);

	@Override
	public void setChartRange(int w, int h, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
		this.mWidth = w - paddingLeft - paddingRight;
		this.mHeight = h - paddingTop - paddingBottom;
		this.mTranslateW = paddingLeft;
		this.mTranslateH = paddingTop;

	}

	public IBorder getBorder(){
		if (mLineBorder ==null){
			mLineBorder = new LineBorder(0,0,mWidth,mHeight);

		}else {
			mLineBorder.setBorderRange(0,0,mWidth,mHeight);
		}

		return mLineBorder;
	}

}

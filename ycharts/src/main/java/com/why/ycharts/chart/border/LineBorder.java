package com.why.ycharts.chart.border;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.why.ycharts.contract.IBorder;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public class LineBorder implements IBorder{
	private float mLeft;
	private float mTop;
	private float mRight;
	private float mBottom;
	private Paint mBorderPaint;
	private boolean mShow;

	public LineBorder(float left, float top, float right, float bottom) {
		this.mLeft = left;
		this.mTop = top;
		this.mRight = right;
		this.mBottom = bottom;
		mBorderPaint = new Paint();
		mBorderPaint.setStyle(Paint.Style.STROKE);
		setBorderColor(Color.RED);
		setBorderWidth(5);
		setShow(true);
	}

	public void setBorderWidth(int width) {
		mBorderPaint.setStrokeWidth(width);
	}
	public void setShow(boolean isShow){
		this.mShow = isShow;
	}

	public boolean isShow() {
		return mShow;
	}
	public void render(Canvas canvas) {

		canvas.drawRect(mLeft,mTop,mRight,mBottom,mBorderPaint);
	}

	public void setBorderColor(int color) {
		mBorderPaint.setColor(color);
	}

	public Paint getPaint(){
		return mBorderPaint;
	}

	public void setBorderRange(float left, float top, float right, float bottom) {
		this.mLeft = left;
		this.mTop = top;
		this.mRight = right;
		this.mBottom = bottom;
	}
}

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
	private int mX;
	private int mY;
	private int mWidth;
	private int mHeight;
	private Paint mBorderPaint;
	private boolean mShow;

	public LineBorder(int x, int y, int width, int height) {
		this.mX = x;
		this.mY = y;
		this.mWidth = width;
		this.mHeight = height;
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
		canvas.drawRect(mX,mY,mWidth,mHeight,mBorderPaint);
	}

	public void setBorderColor(int color) {
		mBorderPaint.setColor(color);
	}

	public Paint getPaint(){
		return mBorderPaint;
	}

	public void setBorderRange(int x, int y, int width, int height) {
		this.mX = x;
		this.mY = y;
		this.mWidth = width;
		this.mHeight = height;
	}
}

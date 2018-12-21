package com.why.ycharts.chart.axis;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public abstract class Axis<T> {
	//类型
	int mType = Type.NONE;

	//用以标明数据位置与step刻度的关系,false则表示刻度与data位置一致
	boolean mBoundaryGap = false;

	//轴线名称位置
	int mNamePostion = NamePosition.MIDDLE;

	boolean mNameVisible = true;

	//刻度长度
	int mMarkLength = 5;

	//刻度画笔
	Paint mMarkPaint;

	//轴线画笔
	Paint mAxisLinePaint;

	boolean mVisible = true;

	boolean mMarkVisible = true;
	boolean mDataVisible = true;

	//轴线标签是否触发事件
	boolean mTriggerEvent = false;

	ArrayList<T> mData = new ArrayList<>();
	Paint mDataPaint;

	public Axis() {
		initAxisLinePaint();
		initMarkPaint();
		initDataPaint();
	}

	private void initDataPaint() {
		if (mDataPaint == null) {
			mDataPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mDataPaint.setColor(Color.RED);
			mDataPaint.setTextSize(30);
		}
	}


	Paint getAxisLinePaint() {
		return mAxisLinePaint;
	}

	private void initAxisLinePaint() {
		if (mAxisLinePaint == null) {
			mAxisLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
	}

	Paint getDataPaint() {
		return mDataPaint;
	}

	Paint getMarkPaint() {
		return mMarkPaint;
	}

	private void initMarkPaint() {
		if (mMarkPaint == null) {
			mMarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		}
	}

	float getTextHeight(Paint paint) {
		Paint.FontMetrics fontMetrics = paint.getFontMetrics();
		return fontMetrics.descent - fontMetrics.ascent;
	}

	float getTextBaseLine(Paint paint) {
		Paint.FontMetrics fontMetrics = paint.getFontMetrics();
		return -fontMetrics.ascent;
	}

	float getTextWidth(Paint paint, String text) {
		return paint.measureText(text);
	}

	public float getMaxTextWidth(Paint paint) {
		float width = 0;
		for (T data : mData) {
			String text = mTransformDataCallback.transformData(data);
			width = Math.max(width,paint.measureText(text));
		}
		return width;
	}

	public void setType(int type) {
		mType = type;
	}

	public abstract void render(Canvas canvas);

	public static class Type {
		public static final int NONE = -1;
	}

	public void setData(ArrayList<T> data) {
		if (this.mData.size() > 0) {
			this.mData.clear();
		}
		this.mData.addAll(data);
	}


	public boolean isDataShow() {
		return mDataVisible;
	}

	public boolean isMarkShow() {
		return mMarkVisible;
	}

	public void setDataShow(boolean isDataShow) {
		mDataVisible = isDataShow;
	}

	public void setMarkShow(boolean isMarkShow) {
		mMarkVisible = isMarkShow;
	}

	public void addData(T data) {
		this.mData.add(data);
	}

	TransformDataCallback<T> mTransformDataCallback;

	public interface TransformDataCallback<T> {

		String transformData(T data);
	}

	public void setTransformDataCallback(@NonNull TransformDataCallback<T> callback) {
		this.mTransformDataCallback = callback;
	}

	public static class NamePosition {
		public static final int START = 0;
		public static final int MIDDLE = 1;
		public static final int END = 1;
	}

}

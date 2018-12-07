package com.why.ycharts.chart.axis;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public abstract class Axis <T>{
	//类型
	int mType = Type.NONE;

	//用以标明数据位置与step刻度的关系
	boolean mBoundaryGap;

	//轴线名称位置
	int mNamePostion = NamePosition.MIDDLE;

	boolean mNameVisibale = true;

	//刻度长度
	int mMarkLentgh = 5;

	//刻度画笔
	Paint mMarkPaint;

	//轴线画笔
	Paint mAxisLinePaint;

	//轴线标签是否触发事件
	boolean mTriggerEvent = false;

	ArrayList<T> mData;

	public Axis() {
		initAxisLinePaint();
		initMarkPaint();
	}

	private Paint getAxisLinePaint() {
		return mAxisLinePaint;
	}

	private void initAxisLinePaint() {
		if (mAxisLinePaint==null){
			mAxisLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
	}

	private Paint getMarkPaint() {
		return mMarkPaint;
	}

	private void initMarkPaint() {
		if (mMarkPaint==null){
			mMarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
	}

	public void setType(int type) {
		mType = type;
	}

	public abstract void render(Canvas canvas);

	public static class Type{
		public static final int NONE = -1;
	}

	public void setData(ArrayList<T> data){
		if (this.mData.size()>0){
			this.mData.clear();
		}
		this.mData.addAll(data);
	}

	public void addData(T data){
		this.mData.add(data);
	}


	public static class  NamePosition{
		public static final int START = 0;
		public static final int MIDDLE = 1;
		public static final int END = 1;
	}

}

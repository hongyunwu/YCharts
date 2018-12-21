package com.why.ycharts.chart.axis;


import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by android_wuhongyun@163.com
 * on 2018/11/29.
 */

public class DataAxis<T> extends Axis<T> {
	private static final String TAG = "DataAxis";
	//默认位置
	int mPosition = Position.BOTTOM;

	float mEdgeLeft = 10, mEdgeRight = 10, mEdgeTop = 10, mEdgeBottom = 10;
	float mEdgeData = 5;
	private float mLeft;
	private float mTop;
	private float mRight;
	private float mBottom;
	private float mStep;

	public DataAxis() {
		setTransformDataCallback(new TransformDataCallback<T>() {

			@Override
			public String transformData(T data) {
				if (data == null) {
					return "NULL";
				}
				return data.toString();
			}
		});
	}

	@Override
	public void render(Canvas canvas) {
		Log.d(TAG, "render: left->" + mLeft + ",right->" + mRight + ",top->" + mTop + ",bottom->" + mBottom);
		renderAxisLine(canvas);
		if (isMarkShow()) {
			renderMark(canvas);
		}
		if (isDataShow()) {
			renderData(canvas);
		}
	}


	private void renderData(Canvas canvas) {
		float diff = 0;
		int size = mData.size();
		switch (mPosition) {
			case Position.LEFT:
				if (isBoundaryGap()) {
					diff = getStep() * 1.0f / 2;
				}
				float maxTextWidthL = getMaxTextWidth(getDataPaint());
				for (int i = 0; i < size; i++) {
					String text = getTransformData(mData.get(i));
					float x = mLeft + mEdgeLeft + mEdgeData + maxTextWidthL - getTextWidth(getDataPaint(), text) - (isMarkShow()?mMarkLength:0);
					float y = mBottom - diff - mEdgeBottom - getStep() * i + (getTextBaseLine(getDataPaint()) - getTextHeight(getDataPaint()) / 2);
					canvas.drawText(text, x, y, getDataPaint());
					Log.d(TAG, "renderData: text->" + text + ",x->" + x + ",y->" + y);
				}
				break;
			case Position.TOP:
				if (isBoundaryGap()) {
					diff = getStep() * 1.0f / 2;
				}
				for (int i = 0; i < size; i++) {
					String text = getTransformData(mData.get(i));
					float x = (diff + mLeft + mEdgeLeft + getStep() * i - getTextWidth(getDataPaint(), text) / 2);
					float y = (mTop + mEdgeTop + getTextBaseLine(getDataPaint()));
					canvas.drawText(text, x, y, getDataPaint());
					Log.d(TAG, "renderData: text->" + text + ",x->" + x + ",y->" + y);
				}
				break;
			case Position.RIGHT:
				if (isBoundaryGap()) {
					diff = getStep() * 1.0f / 2;
				}
				float maxTextWidthR = getMaxTextWidth(getDataPaint());
				for (int i = 0; i < size; i++) {
					String text = getTransformData(mData.get(i));
					float x = mRight -mEdgeRight + mEdgeData - maxTextWidthR + (isMarkShow()?mMarkLength:0);
					float y = mBottom - diff - mEdgeBottom - getStep() * i + (getTextBaseLine(getDataPaint()) - getTextHeight(getDataPaint()) / 2);
					canvas.drawText(text, x, y, getDataPaint());
					Log.d(TAG, "renderData: text->" + text + ",x->" + x + ",y->" + y);
				}
				break;
			case Position.BOTTOM:
				if (isBoundaryGap()) {
					diff = getStep() * 1.0f / 2;
				}
				for (int i = 0; i < size; i++) {
					String text = getTransformData(mData.get(i));
					float x = (diff + mLeft + mEdgeLeft + getStep() * i - getTextWidth(getDataPaint(), text) / 2);
					float y = (mBottom - mEdgeBottom - (getTextHeight(getDataPaint()) - getTextBaseLine(getDataPaint()))) + mEdgeData+(isMarkShow()? mMarkLength :0);
					canvas.drawText(text, x, y, getDataPaint());
					Log.d(TAG, "renderData: text->" + text + ",x->" + x + ",y->" + y);
				}
				break;

		}
	}


	private String getTransformData(T data) {
		String transformData = "NULL";
		if (mTransformDataCallback != null) {
			transformData = mTransformDataCallback.transformData(data);
		} else {
			if (data != null) {
				transformData = data.toString();
			}
		}
		return transformData;
	}

	private float getStep() {
		int size = mData.size();
		if (isBoundaryGap()) {
			size = size + 1;
		}
		if (size <= 0) {
			return -1;
		}
		float step = 0;
		switch (mPosition) {
			case Position.LEFT:
				step = (mBottom - mTop - mEdgeBottom - mEdgeTop) * 1.0f / (size - 1);

				break;
			case Position.TOP:
				step = (mRight - mLeft - mEdgeRight - mEdgeLeft) * 1.0f / (size - 1);

				break;
			case Position.RIGHT:
				step = (mBottom - mTop - mEdgeBottom - mEdgeTop) * 1.0f / (size - 1);

				break;
			case Position.BOTTOM:
				step = (mRight - mLeft - mEdgeRight - mEdgeLeft) * 1.0f / (size - 1);

				break;
		}

		return mStep = step;
	}

	private void renderMark(Canvas canvas) {
		int size = mData.size();
		float step = getStep();
		float diff = 0;

		switch (mPosition) {
			case Position.LEFT:
				if (isDataShow()) {
					diff = getMaxTextWidth(getDataPaint()) + mEdgeData;
				}
				for (int i = 0; i < size; i++) {
					canvas.drawLine(
							mLeft + mEdgeLeft + diff,
							mBottom - mEdgeBottom - step * i,
							mLeft + mEdgeLeft - mMarkLength + diff,
							mBottom - mEdgeBottom - step * i,
							getMarkPaint());
				}
				break;
			case Position.TOP:
				if (isDataShow()) {
					diff = getTextHeight(getDataPaint()) + mEdgeData;
				}
				step = (mRight - mLeft - mEdgeRight - mEdgeLeft) * 1.0f / (size - 1);
				for (int i = 0; i < size; i++) {
					canvas.drawLine(
							mLeft + mEdgeLeft + step * i,
							mTop + mEdgeTop + diff,
							mLeft + mEdgeLeft + step * i,
							mTop + mEdgeTop - mMarkLength + diff,
							getMarkPaint());
				}
				break;
			case Position.RIGHT:
				if (isDataShow()) {
					diff = getMaxTextWidth(getDataPaint()) + mEdgeData;
				}
				step = (mBottom - mTop - mEdgeBottom - mEdgeTop) * 1.0f / (size - 1);
				for (int i = 0; i < size; i++) {
					canvas.drawLine(
							mRight - mEdgeRight - diff,
							mBottom - mEdgeBottom - step * i,
							mRight - mEdgeRight - diff + mMarkLength,
							mBottom - mEdgeBottom - step * i, getMarkPaint());
				}
				break;
			case Position.BOTTOM:
				if (isDataShow()) {
					diff = getTextHeight(getDataPaint()) + mEdgeData;
				}
				step = (mRight - mLeft - mEdgeRight - mEdgeLeft) * 1.0f / (size - 1);
				for (int i = 0; i < size; i++) {
					canvas.drawLine(
							mLeft + mEdgeLeft + step * i,
							mBottom - mEdgeBottom - diff,
							mLeft + mEdgeLeft + step * i,
							mBottom - mEdgeBottom + mMarkLength - diff,
							getMarkPaint());
				}
				break;
		}
		Log.d(TAG, "renderMark: step->" + step + ",position:" + mPosition);
	}

	private void setStep(int step) {
		this.mStep = step;
	}

	public boolean isBoundaryGap() {
		return mBoundaryGap;
	}

	private void renderAxisLine(Canvas canvas) {
		float startX = 0;
		float startY = 0;
		float stopX = 0;
		float stopY = 0;
		float diff = 0;

		switch (mPosition) {
			case Position.LEFT:
				if (isDataShow()) {
					diff = getMaxTextWidth(getDataPaint()) + mEdgeData;
				}
				startX = mLeft + mEdgeLeft + diff;
				startY = mTop + mEdgeTop;
				stopX = mLeft + mEdgeLeft + diff;
				stopY = mBottom - mEdgeBottom;
				break;
			case Position.TOP:
				if (isDataShow()) {
					diff = getTextHeight(getDataPaint()) + mEdgeData;
				}
				startX = mLeft + mEdgeLeft;
				startY = mTop + mEdgeTop + diff;
				stopX = mRight - mEdgeRight;
				stopY = mTop + mEdgeTop + diff;
				break;
			case Position.RIGHT:
				if (isDataShow()) {
					diff = getMaxTextWidth(getDataPaint()) + mEdgeData;
				}
				startX = mRight - mEdgeRight - diff;
				startY = mTop + mEdgeTop;
				stopX = mRight - mEdgeRight - diff;
				stopY = mBottom - mEdgeBottom;
				break;
			case Position.BOTTOM:
				if (isDataShow()) {
					diff = getTextHeight(getDataPaint()) + mEdgeData;
				}
				startX = mLeft + mEdgeLeft;
				startY = mBottom - mEdgeBottom - diff;
				stopX = mRight - mEdgeRight;
				stopY = mBottom - mEdgeBottom - diff;
				break;
		}

		canvas.drawLine(startX, startY, stopX, stopY, getAxisLinePaint());
	}


	public void setRange(float left, float top, float right, float bottom) {

		this.mLeft = left;
		this.mTop = top;
		this.mRight = right;
		this.mBottom = bottom;
	}

	public void setPosition(int position) {
		this.mPosition = position;
	}

	public boolean isShow() {
		return mVisible;
	}

	public void setMarkLength(int length) {
		mMarkLength = length;
	}

	public float getStart() {
		float start = 0;
		switch (mPosition) {
			case Position.LEFT:
				start = mEdgeLeft;
				if (isDataShow()) {
					start += getMaxTextWidth(getDataPaint()) + mEdgeData;
				}
				break;
			case Position.TOP:
				start = mEdgeTop;
				if (isDataShow()) {
					start += getTextHeight(getDataPaint()) + mEdgeData;
				}
				break;
			case Position.RIGHT:
				start = mEdgeRight;
				if (isDataShow()) {
					start += getMaxTextWidth(getDataPaint()) + mEdgeData;
				}
				break;
			case Position.BOTTOM:
				start = mEdgeBottom;
				if (isDataShow()) {
					start += getTextHeight(getDataPaint()) + mEdgeData;
				}
				break;
		}
		return start;
	}


	public float getEnd() {
		float end = 0;
		switch (mPosition) {
			case Position.LEFT:
				end = -1;
				break;
			case Position.TOP:
				end = mEdgeTop;
				if (isDataShow()){
					end +=getTextHeight(getDataPaint())+mEdgeData;
				}
				break;
			case Position.RIGHT:
				end = mEdgeRight;
				if (isDataShow()){
					end +=getMaxTextWidth(getDataPaint())+mEdgeData;
				}
				break;
			case Position.BOTTOM:
				end = -1;
				break;
		}
		return end;
	}

	public void setControlPoint(float start, float end) {
		switch (mPosition) {
			case Position.LEFT:
				if (start > 0) {
					mEdgeBottom = start;
				}
				if (end > 0) {
					mEdgeTop = end;
				}
				break;
			case Position.TOP:
				if (start > 0) {
					mEdgeLeft = start;
				}
				if (end > 0) {
					mEdgeRight = end;
				}
				break;
			case Position.RIGHT:
				if (start > 0) {
					mEdgeBottom = start;
				}
				if (end > 0) {
					mEdgeTop = end;
				}
				break;
			case Position.BOTTOM:
				if (start > 0) {
					mEdgeLeft = start;
				}
				if (end > 0) {
					mEdgeRight = end;
				}
				break;
		}
	}

	public static class Position {
		public static final int TOP = 0;
		public static final int BOTTOM = 1;
		public static final int LEFT = 2;
		public static final int RIGHT = 3;
		public static final int TOP_BOTTOM = 4;
		public static final int LEFT_RIGHT = 5;
	}

	public static class Type extends Axis.Type {
		public static final int VALUE = 0;
		public static final int CATEGORY = 1;
		public static final int TIME = 2;
		public static final int LOG = 3;
	}


}

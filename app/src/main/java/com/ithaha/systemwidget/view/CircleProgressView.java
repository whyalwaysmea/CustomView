package com.ithaha.systemwidget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * 简单的环形进度条
 * Created by HanLong on 2015/12/1.
 */
public class CircleProgressView extends View{

    private int mMeasureWidth;
    private int mMeasureHeight;

    private float mCircleXY;
    private Paint mCirclePaint;
    private float mRadius;

    private RectF mArcRectF;
    private float mSweepValue = 90;
    private float mSweepAngle;
    private Paint mArcPaint;

    private Paint mTextPaint;
    private String mShowText = "";
    private float mShowTextSize;


    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        float length = 0;
        if (mMeasureWidth > mMeasureHeight) {
            length = mMeasureHeight;
        } else {
            length = mMeasureWidth;
        }

        // 圆形
        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));

        // 圆弧
        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9));
        mSweepAngle = mSweepValue;
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(
                android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (length * 0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);

        // 字
        mShowText = getShowText();
        mShowTextSize = setShowTextSize();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }

    // 设置显示的内容
    public void setShowText(String str) {
        this.invalidate();
        if (TextUtils.isEmpty(str)) {
            mShowText = "Android Skill";
        } else {
            mShowText = str;
        }
    }

    private String getShowText() {
        this.invalidate();
        if (TextUtils.isEmpty(mShowText)) {
            return mShowText = "Android Skill";
        } else {
            return mShowText;
        }
    }
    public void forceInvalidate() {
        this.invalidate();
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 10;
        }
        this.invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 决定当前View的大小
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);

        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        // 绘制弧线
        canvas.drawArc(mArcRectF, 0, mSweepAngle, false, mArcPaint);
        // 绘制文字
        canvas.drawText(mShowText, 0, mShowText.length(),
                mCircleXY, mCircleXY + (mShowTextSize / 4), mTextPaint);
    }
}

package com.whyalwaysmea.myview.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.whyalwaysmea.myview.R;

/**
 * Created by HelloWorld on 2017/2/22.
 */

public class RoundProgressBarWithNumber extends HorizontalProgressBar{

    private int mRadius = 100;

    public RoundProgressBarWithNumber(Context context) {
        this(context, null);
    }

    public RoundProgressBarWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBarWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBarWithNumber);
        mRadius = (int) attributes.getDimension(R.styleable.RoundProgressBarWithNumber_radius, mRadius);

        System.out.println("mRadius == " + mRadius);
        attributes.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        // 开启抗锯齿
        mPaint.setAntiAlias(true);
        // 防止颜色抖动
        mPaint.setDither(true);
        // 设置画笔转弯去的连接风格
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 以上三个设置为画圆的固定设置套路
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        // 画笔的宽度
        int paintWidth = Math.max(mReachedProgressBarHeight, mUnReachedProgressBarHeight);

        if(heightMode != MeasureSpec.EXACTLY) {
            int exceptHeight = getPaddingTop() + getPaddingBottom() + paintWidth  + mRadius * 2;

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight, MeasureSpec.EXACTLY);
        }

        if(widthMode != MeasureSpec.EXACTLY) {
            int exceptWidth = getPaddingLeft() + getPaddingRight() + mRadius * 2 + paintWidth;
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth, MeasureSpec.EXACTLY);
        }

        System.out.println("mRadius -------------------- " + mRadius);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress() + "%";

        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mUnReachProgressBarColor);
        mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);

        mPaint.setColor(mReachProgressBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        // 弧度
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(new RectF(0, 0, mRadius * 2, mRadius * 2), 0,
                sweepAngle, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, mRadius - textWidth / 2, mRadius - textHeight,  mPaint);

        canvas.restore();
    }
}

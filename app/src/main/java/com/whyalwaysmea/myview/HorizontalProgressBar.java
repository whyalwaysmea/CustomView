package com.whyalwaysmea.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

/**
 * Created by HelloWorld on 2017/2/22.
 * 横向的ProgressBar
 * 参考链接：http://blog.csdn.net/lmj623565791/article/details/43371299
 */

public class HorizontalProgressBar extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
    private static final int DEFAULT_UNREACHED_COLOR = 0xFFd3d6da;
    private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

    // 画笔
    protected Paint mPaint = new Paint();

    // 默认字体大小
    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    // 默认字体颜色
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    // 默认字体间距
    protected int mTextOffset = dp2px(DEFAULT_SIZE_TEXT_OFFSET);

    // 默认的到达的线条高度
    protected int mReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);
    // 默认的未到达的线条高度
    protected int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);
    // 默认的未到达的线条颜色
    protected int mUnReachProgressBarColor = DEFAULT_UNREACHED_COLOR;
    // 默认的到达的线条颜色
    protected int mReachProgressBarColor = DEFAULT_TEXT_COLOR;

    // Progress的长度
    protected int mRealWidth;

    // 默认是显示字
    protected boolean mIfDrawText = true;

    protected static final int VISIBLE = 0;


    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        setHorizontalScrollBarEnabled(true);

        obtainStyledAttributes(attrs);

        // 画笔属性初始化
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    // 获取xml中的自定义属性
    private void obtainStyledAttributes(AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);

        mTextColor = attributes.getColor(R.styleable.HorizontalProgressBar_progress_text_color, DEFAULT_TEXT_COLOR);
        mReachProgressBarColor = attributes.getColor(R.styleable.HorizontalProgressBar_progress_reached_color, DEFAULT_TEXT_COLOR);
        mUnReachProgressBarColor = attributes.getColor(R.styleable.HorizontalProgressBar_progress_unreached_color, DEFAULT_UNREACHED_COLOR);
        mTextSize = (int) attributes.getDimension(R.styleable.HorizontalProgressBar_progress_text_size, DEFAULT_TEXT_SIZE);
        mReachedProgressBarHeight = (int) attributes.getDimension(R.styleable.HorizontalProgressBar_progress_reached_height, DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);
        mUnReachedProgressBarHeight = (int) attributes.getDimension(R.styleable.HorizontalProgressBar_progress_unreached_height, DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);
        mTextOffset = (int) attributes.getDimension(R.styleable.HorizontalProgressBar_progress_text_offset, DEFAULT_SIZE_TEXT_OFFSET);
        int textVisible = attributes.getInt(R.styleable.HorizontalProgressBar_progress_text_visibility, VISIBLE);
        if(textVisible != VISIBLE) {
            mIfDrawText = false;
        }

        attributes.recycle();
    }


    // 测量高度， 宽度我们不用管
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 如果高度的测量模式不是精准
        // 如果明确指定了高度，我们就不管
        if(heightMode != MeasureSpec.EXACTLY) {

            // 获取到写字所需要的高度
            float textHeight = mPaint.descent() + mPaint.ascent();
            // 1. 判断mReachedProgressBarHeight,mUnReachedProgressBarHeight谁高
            // 2. 判断textHeight 和 1的结果谁高
            // 3. 2的结果为真实的高度
            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math
                    .max(Math.max(mReachedProgressBarHeight,
                            mUnReachedProgressBarHeight), Math.abs(textHeight)));

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight, heightMode);
        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);


        System.out.println("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("onLayout");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        System.out.println("onFinishInflate");
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        System.out.println("onDraw");

        boolean noNeedBg = false;

        // 生成一个新的图层(
        canvas.save();

        // 画笔移动到指定位置
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        // 当前进度和总值的比例
        float radio = getProgress() * (1.0f) / getMax();
        // 已经到达的宽度
        float progressPosX = radio * mRealWidth;
        // 需要绘制的文本内容
        String text = getProgress() + "%";

        // 获取字体的宽高
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

        // 如果到达最后，则未到达的进度条不需要绘制
        if(progressPosX + textWidth > mRealWidth) {
            noNeedBg = true;
            progressPosX = mRealWidth - textWidth;
        }

        // 绘制已经到达的进度
        float endX = progressPosX - mTextOffset / 2;
        if(endX > 0) {
            mPaint.setColor(mReachProgressBarColor);
            mPaint.setStrokeWidth(mReachedProgressBarHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        // 绘制文本
        if(mIfDrawText) {
            mPaint.setColor(mTextColor);
            canvas.drawText(text, progressPosX, -textHeight, mPaint);
        }

        // 绘制未到达的进度条
        if(!noNeedBg) {
            float start = progressPosX + mTextOffset / 2 + textWidth;
            mPaint.setColor(mUnReachProgressBarColor);
            mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("onSizeChanged");

        mRealWidth = w - getPaddingLeft() - getPaddingRight();
    }

    /**
     * dp 2 px
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     * @param spVal
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());

    }
}

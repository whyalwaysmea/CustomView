package com.whyalwaysmea.myview.shoppingcar.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.whyalwaysmea.myview.R;

import java.util.ArrayList;
import java.util.List;

import static com.whyalwaysmea.myview.shoppingcar.view.OrderButton.STEP_BTN_EXPAND;
import static com.whyalwaysmea.myview.shoppingcar.view.OrderButton.STEP_BTN_SHRINK;

/**
 * Created by HelloWorld on 2017/2/26.
 * 1. 先测量宽高， 宽就是根据文字的宽度测量而来
 * 2. 画字和画加号，减号。分成两种情况
 * 3. 触摸事件处理
 */

public class ShoppingView extends View{

    // 默认状态
    private static final int STEP_NORMAL = 0;
    // 文字收缩
    private static final int STEP_TEXT_SHRINK = 1;
    // 按钮展开
    private static final int STEP_BIN_EXPAND = 2;
    // 按钮收缩
    private static final int STEP_BIN_SHRINK = 3;
    // 文字展开
    private static final int STEP_TEXT_EXPAND = 4;
    // 数量变化
    private static final int STEP_TEXT_CHANGE = 5;

    // 显示的文字内容
    private static final String TEXT = "加入购物车";
    // 当前的状态
    private int mType = STEP_NORMAL;
    // 数量
    private int mNum = 0;
    // 最大的数量
    private int mMaxNum = Integer.MAX_VALUE;

    // 绘制背景，按钮，文字的画笔
    private Paint mPaintBg, mPaintNum, mPaintText;

    private int mWidth;         // 宽
    private int mHeight;        // 高
    private float mRadius;      // 半径
    private int mChangeValue;   // 做动画的宽度
    private int mChange;        // 显示数量的矩形宽度
    private boolean mIsRun;     // 是否正在做动画
    private List<Animator> mAnimatorList;
    private float mDegrees;     // 旋转角度


    public ShoppingView(Context context) {
        this(context, null);
    }

    public ShoppingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShoppingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true); // 抗锯齿
        mPaintBg.setColor(getContext().getResources().getColor(R.color.colorPrimary)); // 设置画笔颜色

        mPaintNum = new Paint();
        mPaintNum.setAntiAlias(true);
        mPaintNum.setTextAlign(Paint.Align.CENTER);
        mPaintNum.setTextSize(sp2px(16));
        mPaintNum.setColor(getContext().getResources().getColor(R.color.colorAccent)); // 设置画笔颜色

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(sp2px(16));
        mPaintText.setColor(getContext().getResources().getColor(android.R.color.white)); // 设置画笔颜色

        mWidth = (int) (getTextWidth(mPaintText, TEXT) * 1.2);
        mHeight = sp2px(16) * 2;

        mRadius = mHeight / 2;
        mChangeValue = mChange = (int) (mWidth - (mRadius * 2));

    }

    private int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * @return 获取Text的宽度
     */
    private float getTextWidth(Paint paint, String str) {
        if(!TextUtils.isEmpty(str)) {
            return paint.measureText(str);
        }
        return 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        System.out.println("onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("onDraw");

        super.onDraw(canvas);
        switch (mType) {
            case STEP_NORMAL:
            case STEP_TEXT_SHRINK:
            case STEP_TEXT_EXPAND: {
                // 画右边的圆
                int cx = (int) (mWidth - mRadius);
                int cy = (int) (mHeight - mRadius);
                canvas.drawCircle(cx, cy, mRadius, mPaintBg);
                // 画显示数量的矩形框
                float left = cx - mChange;
                float right = cx;
                float top = cy - mRadius;
                float bottom = cy + mRadius;
                canvas.drawRect(left, top, right, bottom, mPaintBg);
                // 画左边的半圆
                cx = (int) left;
                canvas.drawCircle(cx, cy, mRadius, mPaintBg);
            }
                break;
            case STEP_TEXT_CHANGE:
            case STEP_BTN_EXPAND:
            case STEP_BTN_SHRINK: {
                canvas.save();
                int cx = (int) (mWidth - mRadius);
                int cy = (int) (mHeight - mRadius);
                // 画布移动
                canvas.translate(-mChange, 0);
                if (mType != STEP_TEXT_CHANGE)
                    canvas.rotate(mDegrees, cx, cy);
                mPaintBg.setStrokeWidth(5);
                mPaintBg.setStyle(Paint.Style.STROKE);
                // 减号的圆
                canvas.drawCircle(cx, cy, mRadius - 10, mPaintBg);
                mPaintBg.setStyle(Paint.Style.FILL);
                //绘制减号
                canvas.drawLine(cx + mRadius / 3, cy, cx - mRadius / 3, cy, mPaintBg);
                canvas.restore();
                canvas.save();

                // 绘制数量
                canvas.translate(- (mChange / 2), 0);
                canvas.drawText(String.valueOf(mNum), cx, cy + 12, mPaintNum);
                canvas.restore();

                canvas.drawCircle(cx, cy, mRadius - 7, mPaintBg);
                if (mType == STEP_BTN_EXPAND || mType == STEP_TEXT_CHANGE) {
                    //绘制加号
                    mPaintText.setStrokeWidth(5);
                    canvas.drawLine(cx + mRadius / 3, cy, cx - mRadius / 3, cy, mPaintText);
                    canvas.drawLine(cx, cy - mRadius / 3, cx, cy + mRadius / 3, mPaintText);
                    mPaintText.setStrokeWidth(0);
                }
            }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 如果现在是纯文字显示，也没有做动画
                if(mType == STEP_NORMAL && !mIsRun) {
                    // 数量变成1
                    mNum = 1;
                    // 状态变成文字正在收缩
                    mType = STEP_TEXT_SHRINK;
                    // 正在进行动画
                    mIsRun = true;
                    step1();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    // 文字收缩
    private void step1() {
        if(mType == STEP_TEXT_SHRINK) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(mChangeValue, 0);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if(mIsRun) {
                        mChange = (int) animation.getAnimatedValue();
                        System.out.println("mChangeValue == " + mChangeValue);
                        // 重绘View
                        invalidate();
                    } else {
                        animation.cancel();
                    }
                }
            });
            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mIsRun = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    // 动画完成
                    mType = STEP_BIN_EXPAND;
                    // TODO 回调

                    // 第二个动画
                    step2();
                }


                @Override
                public void onAnimationCancel(Animator animation) {
                    // 动画被取消
                    mIsRun = false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            valueAnimator.setDuration(500);
            valueAnimator.start();
            mAnimators().add(valueAnimator);
        }
    }

    // 按钮展开
    private void step2() {
        if(mType == STEP_BIN_EXPAND && mIsRun) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mChangeValue);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if(mIsRun) {
                        mChange = (int) animation.getAnimatedValue();
                        mDegrees = mChange / (float) mChangeValue * 360f;
                        invalidate();
                    } else {
                        animation.cancel();
                    }
                }
            });
            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mIsRun = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    mIsRun = false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            valueAnimator.setDuration(500);
            valueAnimator.start();
            mAnimators().add(valueAnimator);
        }
    }

    private List<Animator> mAnimators() {
        if(mAnimatorList == null) {
            mAnimatorList = new ArrayList<>();
        }
        return mAnimatorList;
    }
}

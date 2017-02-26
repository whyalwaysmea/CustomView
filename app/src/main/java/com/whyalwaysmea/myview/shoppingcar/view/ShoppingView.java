package com.whyalwaysmea.myview.shoppingcar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.whyalwaysmea.myview.R;

import static com.whyalwaysmea.myview.shoppingcar.view.OrderButton.STEP_BTN_EXPAND;
import static com.whyalwaysmea.myview.shoppingcar.view.OrderButton.STEP_BTN_SHRINK;

/**
 * Created by HelloWorld on 2017/2/26.
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

    private int mWidth;     // 宽
    private int mHeight;    // 高
    private float mRadius;    // 半径
    private int mChangeValue;
    private int mChange;    // 显示数量的矩形宽度


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
    }

    @Override
    protected void onDraw(Canvas canvas) {
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
}

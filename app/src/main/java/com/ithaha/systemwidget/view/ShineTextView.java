package com.ithaha.systemwidget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 会闪光的TextView
 * Created by HanLong on 2015/11/17.
 */
public class ShineTextView extends TextView{

    // 线性着色器
    private LinearGradient mLinearGradient;
    // 模版
    private Matrix mGradientMatrix;
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mTranslate = 0;


    public ShineTextView(Context context) {
        this(context, null);
    }

    public ShineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0) {
                // 关键之处：通过getPaint获取当前绘制TextView的Paint对象
                mPaint = getPaint();

                /**
                 x0  The x-coordinate for the start of the gradient line
                 y0  The y-coordinate for the start of the gradient line
                 x1  The x-coordinate for the end of the gradient line
                 y1  The y-coordinate for the end of the gradient line
                 colors  The colors to be distributed along the gradient line
                 positions  May be null. The relative positions [0..1] of each corresponding color in the colors array. If this is null, the the colors are distributed evenly along the gradient line.
                 tile  The Shader tiling mode

                 */
                /**
                 *  第一个,第二个参数表示渐变起点 可以设置起点终点在对角等任意位置
                    第三个,第四个参数表示渐变终点
                    第五个参数表示渐变颜色
                    第六个参数可以为空,表示坐标,值为0-1 new float[] {0.25f, 0.5f, 0.75f, 1 }
                    如果这是空的，颜色均匀分布，沿梯度线。
                    第七个表示平铺方式
                    CLAMP重复最后一个颜色至最后
                    MIRROR重复着色的图像水平或垂直方向已镜像方式填充会有翻转效果
                    REPEAT重复着色的图像水平或垂直方向
                 */
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,new int[]{Color.BLUE, 0xffffffff, Color.BLUE},
                        null, Shader.TileMode.CLAMP);

                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if(mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            // 刷新界面
            postInvalidateDelayed(100);
        }
    }
}

package com.ithaha.systemwidget.animation;

import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Long
 * on 2016/5/5.
 */
public class CustomTV extends Animation {

    private int mCenterWidth;
    private int mCenterHeight;

    // 实现一些初始化的工作
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);

        setDuration(1000);      // 设置默认时长
        setFillAfter(true);     // 动画结束后保留状态
        setInterpolator(new AccelerateInterpolator());      // 设置默认插值器
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
    }

    /**
     *
     * @param interpolatedTime     插值器的时间因子,取值在0到1之间
     * @param t                    矩阵的封装类，一般使用它来获得当前的矩阵Matrix对象，然后对矩阵进行操作，就可以实现动画效果了。
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);
    }
}

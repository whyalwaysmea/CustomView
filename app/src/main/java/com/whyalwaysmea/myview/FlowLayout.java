package com.whyalwaysmea.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by HelloWorld on 2017/2/21.
 * 流式布局
 * 参考链接：http://blog.csdn.net/lmj623565791/article/details/38352503
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取到宽高/测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 记录宽 高
        int width = 0;
        int height = 0;

        // 记录每一行的宽度， width取最大宽度
        int lineWidth = 0;
        // 每一行的高度， 累加起来就是height
        int lineHeight = 0;

        // 获取有多少个子View
        int cCount = getChildCount();

        // 遍历每个子View
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);

            // 测量子View
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            // 获取子View测量到的宽/高
            int childMeasuredWidth = child.getMeasuredWidth();
            int childMeasuredHeight = child.getMeasuredHeight();

            // 得到子View的LayoutParams
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            // 子View所占的宽/高
            int childWidth = childMeasuredWidth + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childMeasuredHeight + layoutParams.topMargin + layoutParams.bottomMargin;

            // 如果加入当前的子View，总的宽度就超过了主View的宽度
            if(lineWidth + childWidth > widthSize) {
                // 比较当前行和子View谁宽，将这个宽度设置给主View的宽度
                width = Math.max(lineWidth, childWidth);
                // 新的一行的宽度
                lineWidth = childWidth;

                // 主View的高度累加
                height = height + lineHeight;
                // 新的一行的高度
                lineHeight = childHeight;
            } else {
                // 行宽累加
                lineWidth = lineWidth + childWidth;
                // 行高设置以最高的为准
                lineHeight = Math.max(lineHeight, childHeight);
            }

            // 最后一个子View
            if(i == cCount -1) {
                width = Math.max(lineWidth, childWidth);
                height += lineHeight;
            }
        }
        // 测量主View的宽高
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width, (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);
    }

    // 记录所有的View
    private List<List<View>> mAllViews = new ArrayList<>();
    // 记录每一行的行高
    private List<Integer> mLineHeights = new ArrayList<>();


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mAllViews.clear();
        mLineHeights.clear();

        // 获取到测量后的宽
        int width = getWidth();
        // 记录一行的View
        List<View> lineViews = new ArrayList<>();

        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();

            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = layoutParams.leftMargin + layoutParams.rightMargin + measuredWidth;
            int childHeight = layoutParams.topMargin + layoutParams.bottomMargin + measuredHeight;

            // 需要换行
            if(childWidth + lineWidth > width) {
                // 记录行高
                mLineHeights.add(lineHeight);
                // 记录该行的View
                mAllViews.add(lineViews);
                // 重置行宽
                lineWidth = 0;
                // 重置每行的View
//                lineViews.clear();
                lineViews = new ArrayList<>();
            }
            // 获取行高
            lineHeight = Math.max(lineHeight, childHeight);
            // 行宽累加
            lineWidth = lineWidth + childWidth;
            // 记录单行的View
            lineViews.add(child);
        }
        // 记录最后一行
        mLineHeights.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            // 获取该行的所有View
            lineViews = mAllViews.get(i);
            // 获得当前行的高度
            lineHeight = mLineHeights.get(i);

            Log.e(TAG, "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e(TAG, "第" + i + "行， ：" + lineHeight);

            // 遍历当前行的View
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                if(view.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();

                // 计算view的left, top, bottom, right
                int childLeft = left + layoutParams.leftMargin;
                int childRight = childLeft + view.getMeasuredWidth();
                int childTop = top + layoutParams.topMargin;
                int childBottom = childTop + view.getMeasuredHeight();

                view.layout(childLeft, childTop, childRight, childBottom);

                Log.e(TAG, childLeft + ", " + childRight + ", " + childTop + ", " + childBottom);

                left = left + view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            left = 0;
            top = top + lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}

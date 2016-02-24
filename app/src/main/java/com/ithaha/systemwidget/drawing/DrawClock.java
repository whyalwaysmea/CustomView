package com.ithaha.systemwidget.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Long
 * on 2016/2/23.
 */
public class DrawClock extends View {

    private int mWidth, mHeight;

    public DrawClock(Context context) {
        this(context, null);
    }

    public DrawClock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        // 画外圆
        Paint patinCircle = new Paint();
        patinCircle.setStyle(Paint.Style.STROKE);       // 设置画笔风格，空心
        patinCircle.setAntiAlias(true);                 // 设置画笔的锯齿效果
        patinCircle.setStrokeWidth(5);                  // 设置空心边框的宽度
        canvas.drawCircle(mWidth/2, mHeight/2, mWidth/2, patinCircle);

        // 画刻度
        Paint paintDegree = new Paint();
        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            // 区分整点和非整点
            if(i==0 || i==6|| i== 12 || i==18) {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                canvas.drawLine(mWidth/2, mHeight/2-mWidth/2, mWidth/2, mHeight/2-mWidth/2+60, paintDegree);
                String s = String.valueOf(i);
                canvas.drawText(s,
                        mWidth/2 - paintDegree.measureText(s)/2,
                        mHeight/2 - mWidth/2 + 90,
                        paintDegree);
            } else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(mWidth/2, mHeight/2-mWidth/2, mWidth/2, mHeight/2-mWidth/2+30, paintDegree);
                String s = String.valueOf(i);
                canvas.drawText(s,
                        mWidth/2 - paintDegree.measureText(s)/2,
                        mHeight/2 - mWidth/2 + 60,
                        paintDegree);
            }
            // 旋转画布
            canvas.rotate(15, mWidth/2, mHeight/2);
        }

        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
        canvas.translate(mWidth/2, mHeight/2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();
    }


}

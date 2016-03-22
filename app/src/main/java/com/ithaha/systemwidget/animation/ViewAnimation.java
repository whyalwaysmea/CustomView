package com.ithaha.systemwidget.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.ithaha.systemwidget.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewAnimation extends AppCompatActivity {

    @Bind(R.id.aplha)
    Button aplha;
    @Bind(R.id.rotate)
    Button rotate;
    @Bind(R.id.translate)
    Button translate;
    @Bind(R.id.scale)
    Button scale;
    @Bind(R.id.animationset)
    Button animationset;
    @Bind(R.id.objectAnimator)
    Button objectAnimator;
    @Bind(R.id.wrapperview)
    Button wrapperview;
    private AlphaAnimation alphaAnimation;
    private RotateAnimation rotateAnimation;
    private TranslateAnimation translateAnimation;
    private ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.aplha, R.id.rotate, R.id.translate, R.id.scale, R.id.animationset, R.id.objectAnimator, R.id.wrapperview})
    void click(View view) {
        switch (view.getId()) {
            case R.id.aplha:
                // 透明度
                alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(2000);
                aplha.startAnimation(alphaAnimation);
                break;
            case R.id.rotate:
                // 旋转
                rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(2000);
                rotate.startAnimation(rotateAnimation);
                break;
            case R.id.translate:
                // 位移
                translateAnimation = new TranslateAnimation(0, 200, 0, 0);
                translateAnimation.setDuration(2000);
                translateAnimation.setFillAfter(true);
                translate.startAnimation(translateAnimation);
                Toast.makeText(this, "点击了位移动画", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scale:
                // 缩放
                scaleAnimation = new ScaleAnimation(0, 2, 0, 2);
                scaleAnimation.setDuration(2000);
                scaleAnimation.setFillAfter(true);
                scale.startAnimation(scaleAnimation);
                break;
            case R.id.animationset:
                // 动画合集
                AnimationSet animationSet = new AnimationSet(true);
                alphaAnimation = new AlphaAnimation(1, 0);
                animationSet.addAnimation(alphaAnimation);

                rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animationSet.addAnimation(rotateAnimation);

                translateAnimation = new TranslateAnimation(0, 200, 0, 0);
                animationSet.addAnimation(translateAnimation);

                scaleAnimation = new ScaleAnimation(0, 2, 0, 2);
                animationSet.addAnimation(scaleAnimation);

                animationSet.setDuration(3000);
                animationset.startAnimation(animationSet);
                break;
            case R.id.objectAnimator:
                // 属性动画
                ObjectAnimator animator = ObjectAnimator.ofFloat(objectAnimator, "translationX", 0, 300);
                animator.setDuration(3000);
                animator.start();
                Toast.makeText(this, "animator", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wrapperview:
                // 自定义的属性动画
                WrapperView wrapperView = new WrapperView(wrapperview);
                ObjectAnimator.ofInt(wrapperView, "width", 0,500).setDuration(5000).start();
                break;
        }
    }


    private static class WrapperView {
        private View mTarget;

        public WrapperView(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

}

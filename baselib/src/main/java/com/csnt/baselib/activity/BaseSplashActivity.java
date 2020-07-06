package com.csnt.baselib.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sunrain
 * Created Date 2020/7/6 1:03 PM
 */
public abstract class BaseSplashActivity extends BaseActivity {
    @Override
    public void initView() {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setForegroundGravity(Gravity.CENTER);
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        frameLayout.setLayoutParams(layoutParams);
        imageView.setImageResource(setImageResource());
        frameLayout.addView(imageView);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams1.bottomMargin=40;
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        TextView textView = new TextView(this);
        textView.setText(setDevelopInfo());
        linearLayout.addView(textView);
        frameLayout.addView(linearLayout);
        setContentView(frameLayout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(getContext(),setNextClass()));
                    }
                });
            }
        },setDuration());
    }

    protected abstract long setDuration();

    protected abstract String setDevelopInfo();

    protected abstract Class<?> setNextClass();
    protected abstract int setImageResource();
    private Context getContext(){
        return this;
    }
}

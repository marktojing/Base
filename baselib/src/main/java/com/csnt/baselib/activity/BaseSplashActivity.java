package com.csnt.baselib.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by sunrain
 * Created Date 2020/7/6 1:03 PM
 */
public abstract class BaseSplashActivity extends BaseActivity {
    @Override
    public void init() {
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(setImageResource());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(imageView);
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
        },3000);
    }
    protected abstract Class<?> setNextClass();
    protected abstract int setImageResource();
    private Context getContext(){
        return this;
    }
}

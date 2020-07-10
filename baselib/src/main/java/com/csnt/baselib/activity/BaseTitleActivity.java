package com.csnt.baselib.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.csnt.baselib.R;
import com.csnt.baselib.interfaces.BaseTitleInterface;
import com.csnt.dialoglib.AlertDialog;
import com.csnt.dialoglib.SelectDialog;
import com.csnt.dialoglib.StatusDialog;
import com.csnt.titlestatusbar.HeadTitleBar;
import com.csnt.titlestatusbar.viewUtils.StatusBarUtil;

import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sunrain
 * Created Date 2020/6/8 3:58 PM
 */
@SuppressLint("Registered")
public abstract class BaseTitleActivity extends BaseActivity implements BaseTitleInterface {
    HeadTitleBar headerBar;
    LinearLayout linearLayout;
    private Unbinder mUnbinder;

    protected abstract int setLayout();

    public abstract void init();
    public  void initView(){
        setContentView(R.layout.activity_base_title);
        headerBar = (HeadTitleBar) findViewById(R.id.header_bar);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        headerBar.setDefaultTheme();
        headerBar.setLeftListener(v -> {
            finish();
            setAnimator(LEFT_IN_RIGHT_OUT);
        });
        setLayoutInfo(setLayout());
        init();
    };

    public void setTitle(@StringRes int title) {
        headerBar.setTitle(title);
    };

    public void setTitle(String title) {
        headerBar.setTitle(title);
    }

    private void setLayoutInfo(int layout) {
        linearLayout.addView(LayoutInflater.from(linearLayout.getContext()).inflate(layout, linearLayout, false));
        mUnbinder = ButterKnife.bind(this);
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public void hideLeftIcon() {
        headerBar.hideLeftContent();
    }

    public void addAppBarRightIcon(@DrawableRes int drawable, View.OnClickListener onClickListener) {
        headerBar.addRightImage(drawable);
        headerBar.setRightListener(onClickListener);
    }




}

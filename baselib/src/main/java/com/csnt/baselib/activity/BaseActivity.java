package com.csnt.baselib.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.csnt.baselib.entity.BaseOEntity;
import com.csnt.baselib.entity.otherEntity.MainPageEntity;
import com.csnt.titlestatusbar.viewUtils.StatusBarUtil;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by sunrain
 * Created Date 2020/6/8 3:58 PM
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDefaultStatusBar(this);
        BaseOEntity<MainPageEntity,BaseOEntity> baseOEntity = new BaseOEntity<>();
        MainPageEntity mainPageEntity = new MainPageEntity();
        mainPageEntity.setTitle("主页");
        baseOEntity.setT(mainPageEntity);
        init();
    }
    public abstract void init();
}

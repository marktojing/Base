package com.csnt.baselib.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.csnt.baselib.R;
import com.csnt.baselib.entity.BaseNameEntity;
import com.csnt.baselib.entity.BaseOEntity;
import com.csnt.baselib.entity.otherEntity.MainPageEntity;
import com.csnt.baselib.fragmentdialog.BottomSingleSelectFragmentDialog;
import com.csnt.baselib.interfaces.BaseComponentInterface;
import com.csnt.dialoglib.AlertDialog;
import com.csnt.dialoglib.SelectDialog;
import com.csnt.dialoglib.StatusDialog;
import com.csnt.titlestatusbar.viewUtils.StatusBarUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by sunrain
 * Created Date 2020/6/8 3:58 PM
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity implements BaseComponentInterface {
    private StatusDialog statusDialog;
    public final int LEFT_IN_LEFT_OUT = 0x01;
//    public final int LEFT_IN_RIGHT_OUT = 0x02;
//    public final int RIGHT_IN_LEFT_OUT = 0x03;
    public final int RIGHT_IN_RIGHT_OUT = 0x04;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDefaultStatusBar(this);
        initView();
    }
    public abstract void initView();
    public void setAnimator(int type) {
        switch (type) {
            case LEFT_IN_LEFT_OUT:
                setLeftInLeftOutAnim();
                break;
            case LEFT_IN_RIGHT_OUT:
                setLeftInRightOutAnim();
                break;
            case RIGHT_IN_LEFT_OUT:
                setRightInLeftOutAnim();
                break;
            case RIGHT_IN_RIGHT_OUT:
                setRightInRightOutAnim();
                break;
        }

    }

    protected void setLeftInRightOutAnim() {
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    ;

    protected void setLeftInLeftOutAnim() {
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    ;

    protected void setRightInLeftOutAnim() {
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    ;

    protected void setRightInRightOutAnim() {
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    ;
    public void showStatusDialog(String msg, int type) {
        showStatusDialog(msg,type,false);
    }

    public void showStatusDialog(String msg, int type, boolean isCancel) {
        if (statusDialog != null) {
            dismissStatusDialog();
        }
        if (!TextUtils.isEmpty(msg)) {
            statusDialog = StatusDialog.with(this).setCancelable(isCancel)
                    .setPrompt(msg)
                    .setType(type).show();
        } else {
            statusDialog = StatusDialog.with(this).setCancelable(isCancel)
                    .setType(type).show();
        }
    }

    public void dismissStatusDialog() {
        if (statusDialog != null) {
            statusDialog.dismiss();
        }
    }

    public void showSelectDialog(String title, List<SelectDialog.SelectItem> selectItems, SelectDialog.OnItemClick onItemClick) {
        SelectDialog selectDialog = new SelectDialog(this, selectItems);
        selectDialog.setTitle(title);
        selectDialog.setOnItemClick(index -> {
            selectDialog.dismiss();
            onItemClick.click(index);
        });
        selectDialog.show();
    }

    public void showAlertDialog(String title, String content, String rightStr,
                                String leftStr,
                                boolean isCancel,
                                View.OnClickListener onClickListenerR,
                                View.OnClickListener onClickListenerL) {
        AlertDialog.with(this)
                .setCancelable(isCancel)
                .setContent(content)
                .setTitle(title)
                .setPositiveButton(rightStr, onClickListenerR).setNegativeButton(leftStr, onClickListenerL).show();
    }
    /**
     * 界面跳转
     *
     * @param class1   要跳转到的界面
     * @param isFinish 是否结束当前的Activity (true 结束，false 不结束)
     * @param bundle   不需要传参数的时候传入空即可
     */
    @Override
    public void turnActivity(@NonNull Class class1, @NonNull boolean isFinish, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, class1);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        setAnimator(RIGHT_IN_LEFT_OUT);
        if (isFinish) {
            finish();
            setAnimator(LEFT_IN_RIGHT_OUT);
        }
    }

    /**
     * 界面跳转
     *
     * @param class1   要跳转到的界面
     * @param isFinish 是否结束当前的Activity (true 结束，false 不结束)
     */
    protected void turnActivity(@NonNull Class class1, @NonNull boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(this, class1);
        startActivity(intent);
        setAnimator(RIGHT_IN_LEFT_OUT);
        if (isFinish) {
            finish();
            setAnimator(LEFT_IN_RIGHT_OUT);
        }
    }
    @Override
    public void showBottomSingleSelectDialog(String title, List<BaseNameEntity> list,BottomSingleSelectFragmentDialog.OnItemClickListener
                                             onItemClickListener){
        BottomSingleSelectFragmentDialog bottomSingleSelectFragmentDialog = new BottomSingleSelectFragmentDialog(title,list);
        bottomSingleSelectFragmentDialog.setOnItemClickListener(onItemClickListener);
        bottomSingleSelectFragmentDialog.show(getSupportFragmentManager(),title);

    }
}

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
public abstract class BaseTitleActivity extends AppCompatActivity {
    HeadTitleBar headerBar;
    LinearLayout linearLayout;
    private Unbinder mUnbinder;
    private StatusDialog statusDialog;
    public final int LEFT_IN_LEFT_OUT = 0x01;
    public final int LEFT_IN_RIGHT_OUT = 0x02;
    public final int RIGHT_IN_LEFT_OUT = 0x03;
    public final int RIGHT_IN_RIGHT_OUT = 0x04;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
//        setAnimator(RIGHT_IN_LEFT_OUT);
        StatusBarUtil.setDefaultStatusBar(this);
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
    }

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

    protected abstract int setLayout();

    public abstract void init();

    public void setTitle(@StringRes int title) {
        headerBar.setTitle(title);
    }

    ;

    public void showStatusDialog(String msg, int type) {
        if (statusDialog != null) {
            dismissStatusDialog();
        }
        if (!TextUtils.isEmpty(msg)) {
            statusDialog = StatusDialog.with(this).setCancelable(false)
                    .setPrompt(msg)
                    .setType(type).show();
        } else {
            statusDialog = StatusDialog.with(this).setCancelable(false)
                    .setType(type).show();
        }
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
                .setCancelable(true)
                .setContent(content)
                .setTitle(title)
                .setPositiveButton(rightStr, onClickListenerR).setNegativeButton(leftStr, onClickListenerL).show();
    }

    public void setTitle(String title) {
        headerBar.setTitle(title);
    }

    private void setLayoutInfo(int layout) {
        linearLayout.addView(LayoutInflater.from(linearLayout.getContext()).inflate(layout, linearLayout, false));
        mUnbinder = ButterKnife.bind(this);
    }

    ;

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

    /**
     * 界面跳转
     *
     * @param class1   要跳转到的界面
     * @param isFinish 是否结束当前的Activity (true 结束，false 不结束)
     * @param bundle   不需要传参数的时候传入空即可
     */
    protected void turnActivity(@NonNull Class class1, @NonNull boolean isFinish, Bundle bundle) {
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


}

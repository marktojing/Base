package com.csnt.baselib.interfaces;

import android.os.Bundle;
import android.view.View;

import com.csnt.baselib.entity.BaseNameEntity;
import com.csnt.baselib.fragmentdialog.BottomSingleSelectFragmentDialog;
import com.csnt.dialoglib.SelectDialog;
import com.csnt.dialoglib.StatusDialog;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by sunrain
 * Created Date 2020/7/10 10:01 AM
 */
public interface BaseComponentInterface {
    public final int LEFT_IN_RIGHT_OUT = 0x02;
    public final int RIGHT_IN_LEFT_OUT = 0x03;
    void showStatusDialog(String msg, int type, boolean isCancel);
    void dismissStatusDialog();
    void showSelectDialog(String title, List<SelectDialog.SelectItem> selectItems, SelectDialog.OnItemClick onItemClick);
    void showAlertDialog(String title, String content, String rightStr,
                         String leftStr,
                         boolean isCancel,
                         View.OnClickListener onClickListenerR,
                         View.OnClickListener onClickListenerL);

    /**
     * 界面跳转
     *
     * @param class1   要跳转到的界面
     * @param isFinish 是否结束当前的Activity (true 结束，false 不结束)
     */
    void turnActivity(@NonNull Class class1, @NonNull boolean isFinish, Bundle bundle);

    //底部弹出框
    void showBottomSingleSelectDialog(String title, List<BaseNameEntity> list, BottomSingleSelectFragmentDialog.OnItemClickListener onItemClickListener);
}

package com.csnt.baselib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csnt.baselib.R;
import com.csnt.baselib.entity.BaseNameEntity;
import com.csnt.baselib.fragmentdialog.BottomSingleSelectFragmentDialog;
import com.csnt.baselib.interfaces.BaseComponentInterface;
import com.csnt.dialoglib.AlertDialog;
import com.csnt.dialoglib.SelectDialog;
import com.csnt.dialoglib.StatusDialog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sunrain
 * Created Date 2020/7/10 10:16 AM
 */
public abstract class BaseFragment extends Fragment implements BaseComponentInterface {
    private StatusDialog statusDialog;
    private  Unbinder mUnbinder;
    public View mContentView;
    @Override
    public void showStatusDialog(String msg, int type, boolean isCancel) {
        if (statusDialog != null) {
            dismissStatusDialog();
        }
        if( this.getActivity() instanceof AppCompatActivity){
            if (!TextUtils.isEmpty(msg)) {
                statusDialog = StatusDialog.with((AppCompatActivity) this.getActivity()).setCancelable(isCancel)
                        .setPrompt(msg)
                        .setType(type).show();
            } else {
                statusDialog = StatusDialog.with((AppCompatActivity) this.getActivity()).setCancelable(isCancel)
                        .setType(type).show();
            }
        }else{
            //不支持的activity类型
        }

    }

    @Override
    public void dismissStatusDialog() {
        if (statusDialog != null) {
            statusDialog.dismiss();
        }
    }

    @Override
    public void showSelectDialog(String title, List<SelectDialog.SelectItem> selectItems, SelectDialog.OnItemClick onItemClick) {
        SelectDialog selectDialog = new SelectDialog(this.getActivity(), selectItems);
        selectDialog.setTitle(title);
        selectDialog.setOnItemClick(index -> {
            selectDialog.dismiss();
            onItemClick.click(index);
        });
        selectDialog.show();
    }

    @Override
    public void showAlertDialog(String title, String content, String rightStr, String leftStr, boolean isCancel, View.OnClickListener onClickListenerR, View.OnClickListener onClickListenerL) {
        if(this.getActivity() instanceof AppCompatActivity){
            AlertDialog.with((AppCompatActivity) this.getActivity())
                    .setCancelable(isCancel)
                    .setContent(content)
                    .setTitle(title)
                    .setPositiveButton(rightStr, onClickListenerR).setNegativeButton(leftStr, onClickListenerL).show();
        }else{
            //不支持的Activity类型
        }

    }

    public void turnActivity(@NonNull Class class1, @NonNull boolean isFinish) {
        this.turnActivity(class1,isFinish,null);
    }
    @Override
    public void turnActivity(@NonNull Class class1, @NonNull boolean isFinish, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), class1);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
//        setAnimator(RIGHT_IN_LEFT_OUT);
        if (isFinish) {
            this.getActivity().finish();
//            setAnimator(LEFT_IN_RIGHT_OUT);
        }
    }

    @Override
    public void showBottomSingleSelectDialog(String title, List<BaseNameEntity> list, BottomSingleSelectFragmentDialog.OnItemClickListener onItemClickListener) {
        BottomSingleSelectFragmentDialog bottomSingleSelectFragmentDialog = new BottomSingleSelectFragmentDialog(title,list);
        bottomSingleSelectFragmentDialog.setOnItemClickListener(onItemClickListener);
        bottomSingleSelectFragmentDialog.show(getActivity().getSupportFragmentManager(),title);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        mContentView = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mContentView);
        beforeInitView();
        initView(mContentView, savedInstanceState);
        return mContentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected abstract void initView(View mContentView, Bundle savedInstanceState);

    protected abstract void beforeInitView();

    protected abstract int getLayout();
}

package com.csnt.baselib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.csnt.baselib.R;
import com.csnt.baselib.interfaces.BaseTitleInterface;
import com.csnt.titlestatusbar.HeadTitleBar;

import butterknife.ButterKnife;

/**
 * Created by sunrain
 * Created Date 2020/7/10 10:57 AM
 */
public abstract class BaseTitleFragment extends BaseFragment implements BaseTitleInterface {
    HeadTitleBar headerBar;
    LinearLayout linearLayout;
    @Override
    protected void initView(View mContentView, Bundle savedInstanceState) {
        headerBar = (HeadTitleBar) mContentView.findViewById(R.id.header_bar);
        linearLayout = (LinearLayout) mContentView.findViewById(R.id.linear_layout);
        headerBar.setDefaultTheme();
        headerBar.setLeftListener(v -> {
            this.getActivity().finish();
//            setAnimator(LEFT_IN_RIGHT_OUT);
        });
        setLayoutInfo(setLayout());
        init();
    }

    protected abstract void init();

    protected abstract int setLayout();
    private void setLayoutInfo(int layout) {
        linearLayout.addView(LayoutInflater.from(linearLayout.getContext()).inflate(layout, linearLayout, false));
    };
    @Override
    protected int getLayout() {
        return R.layout.activity_base_title;
    }

    @Override
    public void setTitle(int title) {
        headerBar.setTitle(title);
    }

    @Override
    public void setTitle(String title) {
        headerBar.setTitle(title);
    }

    @Override
    public void hideLeftIcon() {
        headerBar.hideLeftContent();
    }

    @Override
    public void addAppBarRightIcon(int drawable, View.OnClickListener onClickListener) {
        headerBar.addRightImage(drawable);
        headerBar.setRightListener(onClickListener);
    }
}

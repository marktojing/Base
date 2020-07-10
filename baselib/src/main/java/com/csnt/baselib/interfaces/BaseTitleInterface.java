package com.csnt.baselib.interfaces;

import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * Created by sunrain
 * Created Date 2020/7/10 11:06 AM
 */
public interface BaseTitleInterface {
    void setTitle(@StringRes int title);
    void setTitle(String title);
    void hideLeftIcon();
    void addAppBarRightIcon(@DrawableRes int drawable, View.OnClickListener onClickListener);
}

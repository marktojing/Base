package com.csnt.baselib.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

/**
 * Created by sunrain
 * Created Date 2020/7/10 11:22 AM
 */
public class BaseNavigationEntity{
    private Fragment fragment;
    @DrawableRes
    private int selectIcon;
    @DrawableRes
    private int unSelectIcon;
    private String text;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }

    public int getUnSelectIcon() {
        return unSelectIcon;
    }

    public void setUnSelectIcon(int unSelectIcon) {
        this.unSelectIcon = unSelectIcon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

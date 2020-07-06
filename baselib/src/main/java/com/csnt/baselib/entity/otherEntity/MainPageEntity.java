package com.csnt.baselib.entity.otherEntity;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.csnt.baselib.entity.BaseEntity;
import com.csnt.titlestatusbar.HeadTitleBar;
import com.google.android.material.internal.NavigationMenu;

/**
 * Created by sunrain
 * Created Date 2020/7/6 10:55 AM
 */
@SuppressLint("ParcelCreator")
public class MainPageEntity implements BaseEntity {
    private String title;
    private HeadTitleBar headTitleBar;
    private NavigationMenu menu;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    public MainPageEntity() {
    }

    protected MainPageEntity(Parcel in) {
        this.title = in.readString();
    }

    public static final Creator<MainPageEntity> CREATOR = new Creator<MainPageEntity>() {
        @Override
        public MainPageEntity createFromParcel(Parcel source) {
            return new MainPageEntity(source);
        }

        @Override
        public MainPageEntity[] newArray(int size) {
            return new MainPageEntity[size];
        }
    };
}

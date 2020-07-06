package com.csnt.baselib.entity;

import android.annotation.SuppressLint;

/**
 * Created by sunrain
 * Created Date 2020/7/3 1:06 PM
 */
@SuppressLint("ParcelCreator")
public class BaseSelfNameEntity extends BaseSelfEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

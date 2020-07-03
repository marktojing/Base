package com.csnt.baselib.entity;

import android.os.Parcel;

/**
 * Created by sunrain
 * Created Date 2020/7/3 1:06 PM
 */
public class BaseNameEntity implements BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public BaseNameEntity() {
    }

    protected BaseNameEntity(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<BaseNameEntity> CREATOR = new Creator<BaseNameEntity>() {
        @Override
        public BaseNameEntity createFromParcel(Parcel source) {
            return new BaseNameEntity(source);
        }

        @Override
        public BaseNameEntity[] newArray(int size) {
            return new BaseNameEntity[size];
        }
    };
}

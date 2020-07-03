package com.csnt.baselib.entity;

import android.annotation.SuppressLint;
import android.os.Parcel;

/**
 * Created by sunrain
 * Created Date 2020/7/3 11:32 AM
 */
@SuppressLint("ParcelCreator")
public  class BaseSelfEntity<T extends BaseSelfEntity> implements BaseEntity {
    public T getBaseSelfEntity() {
        return baseSelfEntity;
    }

    public void setBaseSelfEntity(T baseSelfEntity) {
        this.baseSelfEntity = baseSelfEntity;
    }
    private T baseSelfEntity;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.baseSelfEntity, flags);
    }

    public BaseSelfEntity() {
    }

    protected BaseSelfEntity(Parcel in) {
        this.baseSelfEntity = in.readParcelable(BaseSelfEntity.class.getClassLoader());
    }

}

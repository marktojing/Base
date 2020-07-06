package com.csnt.baselib.entity;

import android.annotation.SuppressLint;
import android.os.Parcel;

import java.util.List;

/**
 * Created by sunrain
 * Created Date 2020/7/6 10:39 AM
 * t:自身属性
 * k:其他列表（可以是自身，也可以是其他类似自身的属性）
 */
@SuppressLint("ParcelCreator")
public class BaseOEntity<T extends BaseEntity,K extends BaseOEntity> implements BaseEntity {
    private List<K> k;
     private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<K> getK() {
        return k;
    }

    public void setK(List<K> k) {
        this.k = k;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

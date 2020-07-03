package com.csnt.baselib.entity;

import android.os.Parcel;
import java.util.List;

/**
 * Created by sunrain
 * Created Date 2020/6/9 1:23 PM
 */
public class BaseRecyclerEntity extends BaseSelfEntity<BaseRecyclerEntity>  {
    private int recyclerType;
    private List<BaseRecyclerEntity> children;
    private Class nextClass;
    private String name;
    private int iconId;

    public int getRecyclerType() {
        return recyclerType;
    }

    public void setRecyclerType(int recyclerType) {
        this.recyclerType = recyclerType;
    }

    public List<BaseRecyclerEntity> getChildren() {
        return children;
    }

    public void setChildren(List<BaseRecyclerEntity> children) {
        this.children = children;
    }

    public Class getNextClass() {
        return nextClass;
    }

    public void setNextClass(Class nextClass) {
        this.nextClass = nextClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.recyclerType);
        dest.writeTypedList(this.children);
        dest.writeSerializable(this.nextClass);
        dest.writeString(this.name);
        dest.writeInt(this.iconId);
    }

    public BaseRecyclerEntity() {
    }

    protected BaseRecyclerEntity(Parcel in) {
        super(in);
        this.recyclerType = in.readInt();
        this.children = in.createTypedArrayList(BaseRecyclerEntity.CREATOR);
        this.nextClass = (Class) in.readSerializable();
        this.name = in.readString();
        this.iconId = in.readInt();
    }

    public static final Creator<BaseRecyclerEntity> CREATOR = new Creator<BaseRecyclerEntity>() {
        @Override
        public BaseRecyclerEntity createFromParcel(Parcel source) {
            return new BaseRecyclerEntity(source);
        }

        @Override
        public BaseRecyclerEntity[] newArray(int size) {
            return new BaseRecyclerEntity[size];
        }
    };
}

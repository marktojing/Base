package com.csnt.baselib.entity.otherEntity;

import androidx.annotation.DrawableRes;

/**
 * Created by sunrain
 * Created Date 2020/7/10 4:12 PM
 */
public class NavigationCenterIconEntity {
    private String text;
    @DrawableRes
    private int centerIcon;
    private int iconSize;
    private int bottomMargin;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCenterIcon() {
        return centerIcon;
    }

    public void setCenterIcon(int centerIcon) {
        this.centerIcon = centerIcon;
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }
}

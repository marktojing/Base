package com.csnt.baselib.interfaces;

import com.csnt.baselib.entity.BaseEntity;
import com.csnt.titlestatusbar.HeadTitleBar;
import com.google.android.material.internal.NavigationMenu;

/**
 * Created by sunrain
 * Created Date 2020/7/6 11:20 AM
 */
public interface BaseMainPageInterface<T extends BaseEntity> extends BasePageInterface {
    void setTitleBar(HeadTitleBar headTitleBar);
    void setNavigation(NavigationMenu menu);
    void setContetPage(T t);
}

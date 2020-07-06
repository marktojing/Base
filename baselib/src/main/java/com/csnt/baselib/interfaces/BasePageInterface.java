package com.csnt.baselib.interfaces;

import com.csnt.baselib.entity.BaseEntity;

/**
 * Created by sunrain
 * Created Date 2020/7/6 11:17 AM
 */
public interface BasePageInterface<T extends BaseEntity> extends BaseInterface {
    void setPageName(String name);
    void setPageId(String id);
    void setPageContent(T t);
}

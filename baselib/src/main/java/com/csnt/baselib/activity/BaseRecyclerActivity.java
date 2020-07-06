package com.csnt.baselib.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.csnt.baselib.R;
import com.csnt.baselib.adapter.BaseRecyclerAdapter;
import com.csnt.baselib.adapter.IconNameAdapter;
import com.csnt.baselib.entity.BaseRecyclerEntity;
import com.csnt.utils.log.LogUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by sunrain
 * Created Date 2020/6/11 9:45 AM
 */
@SuppressLint("Registered")
public class BaseRecyclerActivity extends BaseTitleActivity {

    public final String INTENT_DATA_RECYCLER_BASE = "BaseRecyclerActivity";
    public List<BaseRecyclerEntity> children;
    XRecyclerView recycle;
    LinearLayout linearLayout1;
//    @BindView(R.id.recycle)
//    XRecyclerView recycle;
//    @BindView(R.id.linear_layout1)
//    LinearLayout linearLayout1;

    @Override
    protected int setLayout() {
        return R.layout.activity_recycle_base;
    }

    @Override
    public void init() {
        initViews();
        Intent intent = getIntent();
        BaseRecyclerEntity baseRecyclerEntity = (BaseRecyclerEntity) intent.getParcelableExtra(INTENT_DATA_RECYCLER_BASE);
        children = baseRecyclerEntity.getChildren();
        if (children.size() <= 0) {
            children = new ArrayList<BaseRecyclerEntity>();
        }
        setTitle(baseRecyclerEntity.getName());
        XRecyclerViewUtil.setDefaultXRecycleViewTheme(this, recycle, baseRecyclerEntity.getRecyclerType());

        BaseRecyclerAdapter iconNameAdapter = new IconNameAdapter(R.layout.fruit_item, children);
        iconNameAdapter.setOnItemChildClickListener(this::setItemClickListener);
        recycle.setAdapter(iconNameAdapter);

    }

    private void initViews() {
        recycle=findViewById(R.id.recycle);
        linearLayout1=findViewById(R.id.linear_layout1);
    }

    protected void setItemClickListener(BaseRecyclerAdapter adapter, View view, int position) {
        BaseRecyclerEntity baseSIEntity = children.get(position - 1);
        if (position > 0 && baseSIEntity.getChildren() != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(INTENT_DATA_RECYCLER_BASE, baseSIEntity);
            turnActivity(baseSIEntity.getNextClass(), false, bundle);
        } else {
            LogUtil.d("没有下一步操作了...");
        }
    }

}

package com.csnt.baselib.adapter;

import com.csnt.baselib.R;
import com.csnt.baselib.entity.BaseRecyclerEntity;
import com.csnt.baselib.viewholder.BaseRecycleViewHolder;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by sunrain
 * Created Date 2020/6/11 12:00 AM
 */
public class IconNameAdapter<T extends BaseRecyclerEntity> extends BaseRecyclerAdapter<T> {
    public IconNameAdapter(@Nullable List<T> data) {
        super(R.layout.fruit_item, data);
    }

    @Override
    public void bindViewHolder(@NonNull BaseRecycleViewHolder holder, T item) {
        holder.setNestView(R.id.card_view);
        holder.setText(R.id.fruitname, item.getName());
        holder.setVisible(R.id.fruit_image, true);
        holder.setImageResource(R.id.fruit_image, item.getIconId());
    }
}

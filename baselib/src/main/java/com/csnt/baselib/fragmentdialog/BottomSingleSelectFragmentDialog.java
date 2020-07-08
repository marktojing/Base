package com.csnt.baselib.fragmentdialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csnt.baselib.R;
import com.csnt.baselib.adapter.BaseRecyclerAdapter;
import com.csnt.baselib.entity.BaseNameEntity;
import com.csnt.baselib.viewholder.BaseRecycleViewHolder;
import com.csnt.utils.ui.DensityUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerViewUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//条目固定高度配置44
public class BottomSingleSelectFragmentDialog<T extends BaseNameEntity> extends DialogFragment
{

    private static final String TAG = "xp.chen-DialogFragment";
    private List<T> list;
    private int xrHeight=0;
    private String title="请选择";
    private OnItemClickListener onItemClickListener;


    public BottomSingleSelectFragmentDialog(List<T> list) {
        super();
        this.list = list;
    }
    public BottomSingleSelectFragmentDialog(String title, List<T> list) {
        super();
        this.list = list;
        this.title=title;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        // setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreateView: ");
        //去掉dialog的标题，需要在setContentView()之前
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_fragment_use_demo, container, false);
        View viewById = view.findViewById(R.id.cancel);
        TextView name = view.findViewById(R.id.name);
        name.setText(title);
        viewById.setOnClickListener(v->{
            this.dismiss();
        });
        XRecyclerView xRecyclerView=view.findViewById(R.id.xr);
       if(list!=null&&list.size()>0){
           if(list.size()>7){
               xrHeight=DensityUtil.dip2px(this.getActivity(),7*46+2);
           }else{
               xrHeight=DensityUtil.dip2px(this.getActivity(),list.size()*46+2);
           }
       }
        LinearLayout.LayoutParams layoutParams =new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,xrHeight);
        xRecyclerView.setLayoutParams(layoutParams);
        XRecyclerViewUtil.setDefaultXRecycleViewTheme(this.getActivity(), xRecyclerView, XRecyclerViewUtil.LIST_V);
        BaseRecyclerAdapter<T> baseEntityBaseRecyclerAdapter = new BaseRecyclerAdapter<T>(R.layout.button_n,list) {
            @Override
            public void bindViewHolder(@NonNull BaseRecycleViewHolder holder, T item) {
                holder.setNestView(R.id.button);
                holder.setText(R.id.button,item.getName());
            }
        };
        baseEntityBaseRecyclerAdapter.setOnItemChildClickListener((adapter,v,index)->{
            this.dismiss();
            if(onItemClickListener!=null){
                onItemClickListener.itemClick(index);
            }

        });

        xRecyclerView.setAdapter(baseEntityBaseRecyclerAdapter);

//        LinearLayout linearLayout = view.findViewById(R.id.ll);

        return view;
    }
    public interface OnItemClickListener{
        void itemClick(int index);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
       this.onItemClickListener=onItemClickListener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Window dialogWindow = getDialog().getWindow();
        if (dialogWindow != null) {
            dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
            dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            lp.windowAnimations = android.R.style.Animation_InputMethod;
            dialogWindow.setAttributes(lp);
        }
    }


    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

}

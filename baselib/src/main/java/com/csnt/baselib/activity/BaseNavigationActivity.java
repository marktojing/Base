package com.csnt.baselib.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.csnt.baselib.R;
import com.csnt.baselib.entity.BaseNavigationEntity;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * Created by sunrain
 * Created Date 2020/7/10 11:13 AM
 */
 public abstract class BaseNavigationActivity extends BaseActivity {
    public EasyNavigationBar navigationBar;
    private String[]  tabText;
    private int[]  normalIcon;
    private int[]  selectIcon;
    private List<Fragment>  fragments;
    private List<BaseNavigationEntity>  baseNavigationEntities;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView() {
        setContentView(R.layout.activity_base_navigation);
        baseNavigationEntities=setData();
        distributeData();
        navigationBar= findViewById(R.id.navigation);
        navigationBar.defaultSetting()  //恢复默认配置、可用于重绘导航栏
                .titleItems(tabText)      //  Tab文字集合  只传文字则只显示文字
                .normalIconItems(normalIcon)   //  Tab未选中图标集合
                .selectIconItems(selectIcon)   //  Tab选中图标集合
                .fragmentList(fragments)       //  fragment集合
                .fragmentManager(getSupportFragmentManager())
                .iconSize(20)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(2)     //Tab文字距Tab图标的距离
                .normalTextColor(Color.parseColor("#666666"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#333333"))   //Tab选中时字体颜色
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)  //同 ImageView的ScaleType
                .navigationBackground(Color.parseColor("#80000000"))   //导航栏背景色
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        //Tab点击事件  return true 页面不会切换
//                        ToastUtils.showShort("测试"+position);

                        return false;
                    }

                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        //Tab重复点击事件
                        return false;
                    }
                })
                .smoothScroll(false)  //点击Tab  Viewpager切换是否有动画
                .canScroll(true)    //Viewpager能否左右滑动
                .mode(EasyNavigationBar.NavigationMode.MODE_ADD)   //默认MODE_NORMAL 普通模式  //MODE_ADD 带加号模式
                .centerTextStr("发现")
                .centerImageRes(R.drawable.ic_error)
                .centerIconSize(36)    //中间加号图片的大小
                .centerLayoutHeight(30)   //包含加号的布局高度 背景透明  所以加号看起来突出一块
                .navigationHeight(60)  //导航栏高度
                .lineHeight(1)         //分割线高度  默认1px
                .lineColor(Color.parseColor("#ff0000"))
                .centerLayoutRule(EasyNavigationBar.RULE_BOTTOM) //RULE_CENTER 加号居中addLayoutHeight调节位置 EasyNavigationBar.RULE_BOTTOM 加号在导航栏靠下
                .centerLayoutBottomMargin(10)   //加号到底部的距离
                .hasPadding(true)    //true ViewPager布局在导航栏之上 false有重叠
                .hintPointLeft(-3)  //调节提示红点的位置hintPointLeft hintPointTop（看文档说明）
                .hintPointTop(-3)
                .hintPointSize(6)    //提示红点的大小
                .msgPointLeft(-10)  //调节数字消息的位置msgPointLeft msgPointTop（看文档说明）
                .msgPointTop(-10)
                .msgPointTextSize(9)  //数字消息中字体大小
                .msgPointSize(18)    //数字消息红色背景的大小
                .centerAlignBottom(true)  //加号是否同Tab文字底部对齐  RULE_BOTTOM时有效；
                .centerTextTopMargin(8)  //加号文字距离加号图片的距离
                .centerTextSize(3)      //加号文字大小
                .setOnCenterTabClickListener(new EasyNavigationBar.OnCenterTabSelectListener(){

                    @Override
                    public boolean onCenterTabSelectEvent(View view) {
//                        ToastUtils.showShort("测试111");
                        return false;
                    }
                })
                .centerNormalTextColor(Color.parseColor("#ff0000"))    //加号文字未选中时字体颜色
                .centerSelectTextColor(Color.parseColor("#00ff00"))    //加号文字选中时字体颜色
                .setMsgPointColor(Color.RED) //数字消息、红点背景颜色
                .setMsgPointMoreRadius(5) //消息99+角度半径
                .setMsgPointMoreWidth(50)  //消息99+宽度
                .setMsgPointMoreHeight(40)  //消息99+高度
                .textSizeType(EasyNavigationBar.TextSizeType.TYPE_DP)  //字体单位 建议使用DP  可切换SP
                .setOnTabLoadListener(new EasyNavigationBar.OnTabLoadListener() { //Tab加载完毕回调
                    @Override
                    public void onTabLoadCompleteEvent() {
                        navigationBar.setMsgPointCount(0, 7);
                        navigationBar.setMsgPointCount(1, 109);
                        navigationBar.setHintPoint(4, true);
                    }
                })
                //.setupWithViewPager() ViewPager或ViewPager2
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void distributeData() {
        List<String> tabTextArr;
        List<Integer> normalArr;
        List<Integer> selectArr;
        if(baseNavigationEntities!=null&&baseNavigationEntities.size()>0){
            tabTextArr=new ArrayList<>();
            selectArr=new ArrayList<>();
            normalArr=new ArrayList<>();
            fragments=new ArrayList<>();
            for (BaseNavigationEntity baseNavigationEntity : baseNavigationEntities) {
                tabTextArr.add(baseNavigationEntity.getText());
                normalArr.add(baseNavigationEntity.getUnSelectIcon());
                selectArr.add(baseNavigationEntity.getUnSelectIcon());
                fragments.add(baseNavigationEntity.getFragment());
            }
            tabText= (String[]) tabTextArr.toArray();
            normalIcon=normalArr.stream().mapToInt(Integer::valueOf).toArray();       ;
            selectIcon=selectArr.stream().mapToInt(Integer::valueOf).toArray();

        }else{
            tabText=new String[]{};
            normalIcon=new int[]{};
            selectIcon=new int[]{};
            fragments=new ArrayList<>();
        }
    }

    ;
   abstract List<BaseNavigationEntity>  setData();

}

package cn.com.jinzhong.shandonggrain.android.activity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import cn.com.jinzhong.shandonggrain.R;
import cn.com.jinzhong.shandonggrain.android.base.BaseActivity;
import cn.com.jinzhong.shandonggrain.android.fragment.ChannelFragment;
import cn.com.jinzhong.shandonggrain.android.fragment.FrameworkFragment;
import cn.com.jinzhong.shandonggrain.android.fragment.HomeFragment;
import cn.com.jinzhong.shandonggrain.android.fragment.MineFragment;
import cn.com.jinzhong.shandonggrain.android.fragment.WorkSpaceFragment;
import cn.com.jinzhong.shandonggrain.android.helper.BottomNavigationViewHelper;
/*
* 主页界面
* */
public class MainActivity extends BaseActivity {
    private HomeFragment homeFragment;
    private ChannelFragment channelFragment;
    private FrameworkFragment frameworkFragment;
    private WorkSpaceFragment workSpaceFragment;
    private MineFragment mineFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.na_home:
                    initHomeFragment();
                    return true;
                case R.id.na_channel:
                    initChannelFragment();
                    return true;
                case R.id.na_framework:
                    initFrameworkFragment();
                    return true;
                case R.id.na_workbench:
                    initWorkSpaceFragment();
                    return true;
                case R.id.na_mine:
                    initMineFragment();
                    return true;
            }
            return false;
        }

    };
    //初始化首页界面
    private void initHomeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(homeFragment == null){
            homeFragment = new HomeFragment();
            transaction.add(R.id.content, homeFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(homeFragment);
        transaction.commit();
    }
    //初始化频道界面
    private void initChannelFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(channelFragment == null){
            channelFragment = new ChannelFragment();
            transaction.add(R.id.content, channelFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(channelFragment);
        transaction.commit();
    }
    //初始化组织架构界面
    private void initFrameworkFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(frameworkFragment == null){
            frameworkFragment = new FrameworkFragment();
            transaction.add(R.id.content, frameworkFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(frameworkFragment);
        transaction.commit();
    }
    //初始化工作台界面
    private void initWorkSpaceFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(workSpaceFragment == null){
            workSpaceFragment = new WorkSpaceFragment();
            transaction.add(R.id.content, workSpaceFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(workSpaceFragment);
        transaction.commit();
    }
    //初始化我的界面
    private void initMineFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mineFragment == null){
            mineFragment = new MineFragment();
            transaction.add(R.id.content, mineFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(mineFragment);
        transaction.commit();
    }
    //使用反射 设置底部布局
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void init() {
        super.init();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };
        int[] colors = new int[]{getResources().getColor(R.color.home_bottom_normal),
                getResources().getColor(R.color.home_bottom_checked)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        navigation.setItemTextColor(csl);
        navigation.setItemIconTintList(csl);
        initHomeFragment();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(channelFragment != null){
            transaction.hide(channelFragment);
        }
        if(frameworkFragment != null){
            transaction.hide(frameworkFragment);
        }
        if(workSpaceFragment != null){
            transaction.hide(workSpaceFragment);
        }
        if(mineFragment != null){
            transaction.hide(mineFragment);
        }
    }
}

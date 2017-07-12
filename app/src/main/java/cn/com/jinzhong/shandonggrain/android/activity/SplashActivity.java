package cn.com.jinzhong.shandonggrain.android.activity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import butterknife.InjectView;
import cn.com.jinzhong.shandonggrain.R;
import cn.com.jinzhong.shandonggrain.android.base.BaseActivity;
import cn.com.jinzhong.shandonggrain.common.utils.SharedPreferencesUtils;
import cn.com.jinzhong.shandonggrain.manager.ShandongGrain;

/*
* 欢迎页
* */
public class SplashActivity extends BaseActivity {
    @InjectView(R.id.ll_root)
    LinearLayout llRoot;
    private boolean loginFlag;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        // 动画效果
        final AlphaAnimation alpha = new AlphaAnimation(0.3f, 1.0f);
        alpha.setDuration(2000);
        llRoot.startAnimation(alpha);
        alpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                //if(flag == 1){
                checkLogin();
                //}
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }
    /*
    * 监测是否已登录
    * */
    private void checkLogin() {
        loginFlag = SharedPreferencesUtils.getBoolean(getBaseContext(),
                ShandongGrain.ALREADY_LOGIN_FLAG);//获取是否登录
        if (loginFlag){
            launchMainActivity();
        }else {
            launchLoginActivity();
        }
    }
    /*
    * 进入登陆界面
    * */
    private void launchLoginActivity() {
        startActivity(LoginActivity.class,true);
    }

    //进入主页面
    private void launchMainActivity() {
        startActivity(MainActivity.class,true);
    }

}

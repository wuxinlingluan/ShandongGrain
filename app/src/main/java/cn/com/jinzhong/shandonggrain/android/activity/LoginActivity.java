package cn.com.jinzhong.shandonggrain.android.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.ParseException;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.jinzhong.shandonggrain.R;
import cn.com.jinzhong.shandonggrain.android.base.BaseActivity;
import cn.com.jinzhong.shandonggrain.common.utils.CommonUtil;
import cn.com.jinzhong.shandonggrain.common.utils.MD5Tools;
import cn.com.jinzhong.shandonggrain.common.utils.SharedPreferencesUtils;
import cn.com.jinzhong.shandonggrain.manager.ShandongGrain;
import cn.com.jinzhong.shandonggrain.manager.callback.JsonCallback;
import cn.com.jinzhong.shandonggrain.manager.callback.LoginCallBackBean;

/*
* 登陆界面
* */
public class LoginActivity extends BaseActivity {
    private static final int LOGIN_SUCCESS = 1 << 0;
    private static final int LOGIN_FAILED = 1 << 1;
    private static final int SERVER_ERROR = 1 << 2;
    @InjectView(R.id.userName)
    EditText userName;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.register)
    TextView register;
    @InjectView(R.id.Login)
    Button Login;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();

    }

    @OnClick({R.id.register, R.id.Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register://注册
                startActivity(RegisterActivity.class,true);
                break;
            case R.id.Login://登陆
                String mName = userName.getText().toString().trim();
                String mPassWord = password.getText().toString().trim();
                if (TextUtils.isEmpty(mName)){
                    toast("账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mPassWord)){
                    toast("密码不能为空");
                    return;
                }
                try {
                    if (CommonUtil.checkIDCard(mName)){
                        String md5Pwd = MD5Tools.getMD5(mPassWord);
                        login(mName,md5Pwd);
                    }else {
                        toast("请输入正确的身份证号");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            // 关闭对话框
            hideProgress();
            switch (msg.what) {
                case LOGIN_SUCCESS:
                    boolean isNotFirst = SharedPreferencesUtils.getBoolean(getBaseContext(),
                            ShandongGrain.IS_NOT_FIRST_LOGIN);//获取是否登录
                    if (isNotFirst){
                        startActivity(MainActivity.class,true);
                        SharedPreferencesUtils.putBoolean(getBaseContext(),
                                ShandongGrain.ALREADY_LOGIN_FLAG, true);//是否登录
                    } else {
                        //验证
                        startActivity(VerifyActivity.class,true);
                        SharedPreferencesUtils.putBoolean(getBaseContext(),
                                ShandongGrain.IS_NOT_FIRST_LOGIN, true);//是否登录
                    }
                    break;
                case LOGIN_FAILED:
                    toast("登陆失败");
                    break;
                case SERVER_ERROR:
                    toast("系统繁忙,请稍后再试");
                    break;
            }
        }
    };


    /*
    * 登陆操作
    * */
    private void login(String mName, String md5Pwd) {
        showProgress("正在登陆");
        String url= ShandongGrain.BASE_URL+"/APPInterfaceServices/LoginService.ashx";
        OkGo.<LoginCallBackBean>post(url).tag(this).params("UserName",mName).params("Password",md5Pwd).execute(new JsonCallback<LoginCallBackBean>(LoginCallBackBean.class) {
            @Override
            public void onSuccess(Response<LoginCallBackBean> response) {
                LoginCallBackBean body = response.body();
                if (response == null) {
                    // 发个服务器错误的消息
                    mHandler.sendEmptyMessageDelayed(SERVER_ERROR, 500);
                    return;
                }
                if (response.isSuccessful()) {
                    Message msg = mHandler.obtainMessage();
                    msg.what = LOGIN_SUCCESS;
                    msg.obj = body;
                    Log.e("--login-", body.toString());
                    mHandler.sendMessageDelayed(msg, 500);
                } else {
                    // 发个登陆失败的消息
                    Message message = mHandler.obtainMessage();
                    message.what = LOGIN_FAILED;
                    message.obj = body.getMsg();
                    mHandler.sendMessageDelayed(message, 500);
                }
            }
        });
    }
}

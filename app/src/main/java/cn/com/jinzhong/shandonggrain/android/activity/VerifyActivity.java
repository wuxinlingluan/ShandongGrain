package cn.com.jinzhong.shandonggrain.android.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.jinzhong.shandonggrain.R;
import cn.com.jinzhong.shandonggrain.android.base.BaseActivity;
import cn.com.jinzhong.shandonggrain.common.utils.CommonUtil;
import cn.com.jinzhong.shandonggrain.common.utils.ResendCheckCodeTimer;
import cn.com.jinzhong.shandonggrain.manager.ShandongGrain;
import cn.com.jinzhong.shandonggrain.manager.callback.CommonCallBackBean;
import cn.com.jinzhong.shandonggrain.manager.callback.JsonCallback;

//验证界面
public class VerifyActivity extends BaseActivity {
    private static final int SUCCESS = 1 << 0;
    private static final int FAILED = 1 << 1;
    private static final int SERVER_ERROR = 1 << 2;
    private ResendCheckCodeTimer checkCodeTimer;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ver_phone)
    EditText verPhone;
    @InjectView(R.id.ver_btn)
    Button verBtn;
    @InjectView(R.id.ver_code)
    EditText verCode;
    @InjectView(R.id.ver_submit)
    Button verSubmit;
    @InjectView(R.id.container)
    LinearLayout container;
    private String verCodeNum;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_verify;
    }

    @Override
    protected void init() {
        super.init();
        checkCodeTimer = new ResendCheckCodeTimer(60000, 1000,
                verBtn);
    }

    @OnClick({R.id.ver_btn, R.id.ver_submit})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.ver_btn://获取验证码
                    checkCodeTimer.start();
                    getAuthCode();
                    break;
                case R.id.ver_submit://验证
                    if (verCode.getText().toString().equals(verCodeNum)) {
                        startActivity(MainActivity.class, true);
                    } else {
                        toast("验证码输入错误");
                    }
                    break;
            }
        }
    /*
    * 获取验证码
    * */
    private void getAuthCode() {
        final String phone = verPhone.getText().toString();
        String url = ShandongGrain.BASE_URL + "/APPInterfaceServices/SendShortMessageService.ashx";
        verCodeNum = (int) ((Math.random() * 9 + 1) * 100000) + "";
        if (CommonUtil.isPhone(phone)) {
            OkGo.<CommonCallBackBean>post(url).params("Phone", phone).params("Content", verCodeNum).execute(new JsonCallback<CommonCallBackBean>(CommonCallBackBean.class) {
                @Override
                public void onSuccess(Response<CommonCallBackBean> response) {
                    CommonCallBackBean body = response.body();
                    if (response == null) {
                        // 发个服务器错误的消息
                        mHandler.sendEmptyMessageDelayed(SERVER_ERROR, 500);
                        return;
                    }
                    if (response.isSuccessful()) {
                        Message msg = mHandler.obtainMessage();
                        msg.what = SUCCESS;
                        msg.obj = body;
                        Log.e("--login-", body.toString());
                        mHandler.sendMessageDelayed(msg, 500);
                    } else {
                        // 发个登陆失败的消息
                        Message message = mHandler.obtainMessage();
                        message.what = FAILED;
                        message.obj = body.getMsg();
                        mHandler.sendMessageDelayed(message, 500);
                    }
                }
            });
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            // 关闭对话框
            hideProgress();
            switch (msg.what) {
                case SUCCESS:
                    toast("验证码已发送");
                    break;
                case FAILED:
                    toast("验证码发送失败");
                    break;
                case SERVER_ERROR:
                    toast("系统繁忙,请稍后再试");
                    break;
            }
        }
    };
}
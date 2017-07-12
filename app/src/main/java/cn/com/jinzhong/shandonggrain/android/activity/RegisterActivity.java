package cn.com.jinzhong.shandonggrain.android.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.jinzhong.shandonggrain.R;
import cn.com.jinzhong.shandonggrain.android.base.BaseActivity;
import cn.com.jinzhong.shandonggrain.common.utils.CommonUtil;
import cn.com.jinzhong.shandonggrain.common.utils.MD5Tools;
import cn.com.jinzhong.shandonggrain.manager.ShandongGrain;
import cn.com.jinzhong.shandonggrain.manager.callback.CommonCallBackBean;
import cn.com.jinzhong.shandonggrain.manager.callback.JsonCallback;

/*
* 注册界面
* */
public class RegisterActivity extends BaseActivity {
    private static final int SUCCESS = 1 << 0;
    private static final int FAILED = 1 << 1;
    private static final int SERVER_ERROR = 1 << 2;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.text_real_name)
    EditText textRealName;
    @InjectView(R.id.iv_id)
    ImageView ivId;
    @InjectView(R.id.text_card)
    EditText textCard;
    @InjectView(R.id.img_upload)
    ImageView imgUpload;
    @InjectView(R.id.text_pwd_1)
    EditText textPwd1;
    @InjectView(R.id.text_pwd_2)
    EditText textPwd2;
    @InjectView(R.id.text_phone_1)
    EditText textPhone1;
    @InjectView(R.id.text_phone_2)
    EditText textPhone2;
    @InjectView(R.id.registerBtn)
    Button registerBtn;
    @InjectView(R.id.container)
    LinearLayout container;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_register;
    }
    @Override
    protected void init() {
        super.init();
    }
    @OnClick({R.id.img_upload, R.id.registerBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_upload://拍照
                toast("上传照片");
                break;
            case R.id.registerBtn://注册

                String mName = textRealName.getText().toString().trim();
                String mCard = textCard.getText().toString().trim();
                String mPwd = textPwd1.getText().toString().trim();
                String mPhone = textPhone1.getText().toString().trim();
                String mPicPath="";
                if (TextUtils.isEmpty(mName)) {
                    toast("真实姓名不能为空");
                } else if (TextUtils.isEmpty(mCard)) {
                    toast("读取身份证失败");
                } else if (TextUtils.isEmpty(mPwd)) {
                    toast("请输入密码");
                } else if (TextUtils.isEmpty(textPwd2.getText().toString())) {
                    toast("请输入确认密码");
                } else if (TextUtils.isEmpty(mPhone)) {
                    toast("请输入手机号");
                } else if (TextUtils.isEmpty(textPhone2.getText().toString())) {
                    toast("请输入确认手机号");
                } else if (!textPwd1.getText().toString().equals(textPwd2.getText().toString())) {
                    toast("两次输入的密码不同。请重新输入");
                } else if (!CommonUtil.isPhone(textPhone1.getText().toString())) {
                    toast("请输入正确的手机号");
                } else if (!textPhone1.getText().toString().equals(textPhone2.getText().toString())) {
                    toast("两次输入的手机号不同。请重新输入");
                } else {
                    try {
                        if (CommonUtil.checkIDCard(textCard.getText().toString())) {
                            String md5Pwd = MD5Tools.getMD5(textPwd1.getText().toString());
                         //   startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            showProgress("正在注册");
                            register(mName,mCard,md5Pwd,mPhone,mPicPath);
                        } else {
                            toast("请上传正确的身份证");
                        }
                    } catch (java.text.ParseException e) {
                        toast("验证身份证异常");
                    }
                }
                break;
        }
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            // 关闭对话框
            hideProgress();
            switch (msg.what) {
                case SUCCESS:
                  toast("注册成功");
                    startActivity(LoginActivity.class,true);
                    break;
                case FAILED:
                    toast("注册失败");
                    break;
                case SERVER_ERROR:
                    toast("系统繁忙,请稍后再试");
                    break;
            }
        }
    };
    /*
    * 注册
    * */
    private void register(String mName, String mCard, String mPwd, String mPhone,String mPicPath) {
        String url= ShandongGrain.BASE_URL+"/APPInterfaceServices/RegisterService.ashx";
        //startActivity(LoginActivity.class,true);
        OkGo.<CommonCallBackBean>post(url).tag(this).params("IdentityCode",mCard).
                params("RealName",mName).params("Phone",mPhone).params("IdentityImgPath",mPicPath).params("Password",mPwd).execute(new JsonCallback<CommonCallBackBean>(CommonCallBackBean.class) {
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

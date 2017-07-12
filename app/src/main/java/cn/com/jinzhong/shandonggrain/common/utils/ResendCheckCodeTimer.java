package cn.com.jinzhong.shandonggrain.common.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by ${sheldon} on 2017/7/11.
 */

public class ResendCheckCodeTimer extends CountDownTimer {
    private Button mButton;
    public ResendCheckCodeTimer(long millisInFuture, long countDownInterval,
                                Button mButton) {
        super(millisInFuture, countDownInterval);
        this.mButton = mButton;
    }
    /**
     * 正在计时
     */
    @Override
    public void onTick(long millisUntilFinished) {
        // 设置按键无效状态
        mButton.setText(millisUntilFinished / 1000 + "s" + "后重发");
        mButton.setEnabled(false);
    }

    /**
     * 计时结束
     */
    @Override
    public void onFinish() {
        // ----恢复按键有效---------------------------
        mButton.setText("重新发送");
        mButton.setEnabled(true);
    }
}

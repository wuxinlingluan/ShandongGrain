package cn.com.jinzhong.shandonggrain.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ${sheldon} on 2017/7/10.
 */

public class ToastUtils {
    /**
     * 弹土司
     *
     * @param context
     * @param msg
     */
    public static void show(final Activity context, final String msg) {
        // 判断当前线程是为主线程
        if (Thread.currentThread().getName().equals("main")) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            // 不是主线程,调用activity的runonuithread方法
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private static Toast sToast;

    public static void showToast(Context context, String msg){
        if (sToast==null){
            sToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        //如果这个Toast已经在显示了，那么这里会立即修改文本
        sToast.setText(msg);
        sToast.show();
    }


    /**
     * 居中弹土司
     *
     * @param context
     * @param msg
     */
    public static void showInCenter(final Activity context, final String msg) {

        // 判断当前线程是为主线程
        if (Thread.currentThread().getName().equals("main")) {

            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            // 不是主线程,调用activity的runonuithread方法
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(context, msg,
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
        }
    }


}

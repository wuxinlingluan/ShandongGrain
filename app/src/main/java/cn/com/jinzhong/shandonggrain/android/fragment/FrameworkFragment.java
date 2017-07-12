package cn.com.jinzhong.shandonggrain.android.fragment;

import android.support.v7.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.InjectView;
import cn.com.jinzhong.shandonggrain.R;
import cn.com.jinzhong.shandonggrain.android.base.BaseFragment;

/**
 * Created by ${sheldon} on 2017/7/10.
 */

public class FrameworkFragment extends BaseFragment {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_framework;
    }

    @Override
    protected void initView() {
        super.initView();
        ImmersionBar.with(this).titleBar(toolbar)
                .init();
    }

}

package cn.com.jinzhong.shandonggrain.android.fragment;

import android.support.v7.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;
import cn.com.jinzhong.shandonggrain.R;
import cn.com.jinzhong.shandonggrain.android.base.BaseFragment;
import cn.com.jinzhong.shandonggrain.manager.GlideImageLoader;

/**
 * Created by ${sheldon} on 2017/7/10.
 */

public class HomeFragment extends BaseFragment {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.banner)
    Banner banner;
    private String[] images = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
    List list = Arrays.asList(images);
    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        ImmersionBar.with(this).titleBar(toolbar)
                .init();
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}

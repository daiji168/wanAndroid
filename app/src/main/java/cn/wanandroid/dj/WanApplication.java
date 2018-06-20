package cn.wanandroid.dj;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.wanandroid.dj.application.AppContext;
import cn.wanandroid.dj.utils.ToastUtil;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class WanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        ToastUtil.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    private void init(){
        //初始化App配置
        AppContext.initialize(this);
    }
}

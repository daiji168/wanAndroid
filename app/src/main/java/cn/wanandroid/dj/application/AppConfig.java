package cn.wanandroid.dj.application;

import android.content.Context;

import cn.wanandroid.dj.constants.HttpConstants;
import cn.wanandroid.dj.net.RxRetrofit;
import cn.wanandroid.dj.utils.PreUtils;


public class AppConfig {

    static void init(Context context){
        //初始化网络框架
        RxRetrofit.getInstance().initRxRetrofit(context, HttpConstants.baseUrl);
        //初始化缓存
        PreUtils.init(context);
    }

}

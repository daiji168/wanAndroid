package cn.wanandroid.dj.mvp.model;

import cn.wanandroid.dj.api.ApiServer;
import cn.wanandroid.dj.net.RxRetrofit;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class BaseModel {

    protected  ApiServer doRxRequest() {
        return RxRetrofit.Api();
    }
}

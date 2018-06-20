package cn.wanandroid.dj.net.callback;


import cn.wanandroid.dj.bean.ResponModel;
import cn.wanandroid.dj.net.NetConfig;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 用来处理嵌套请求的操作
 * author: 康栋普
 * date: 2018/3/7
 */

public abstract class RxFunction<T, R> implements Function<ResponModel<T>, Observable<ResponModel<R>>> {

    @Override
    public Observable<ResponModel<R>> apply(ResponModel<T> tBaseBean) throws Exception {
        if (tBaseBean.getErrorCode() == NetConfig.REQUEST_SUCCESS){
            return doOnNextRequest();
        }
        return null;
    }

    protected abstract Observable<ResponModel<R>> doOnNextRequest();

}

package cn.wanandroid.dj.net.callback;

import cn.wanandroid.dj.bean.ResponModel;
import cn.wanandroid.dj.net.NetConfig;

/**
 * 通用Observer回调
 */

public abstract class RxObserver<T> extends RxBaseObserver<T> {
    @Override
    public void onNext(ResponModel<T> responModel) {
        //请求成功
        if (responModel.getErrorCode() == NetConfig.REQUEST_SUCCESS) {
            onSuccess(responModel.getData());
        } else {
         //失败
            onFail(responModel.getErrorCode(), responModel.getErrorMsg());
        }
    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(int errorCode, String errorMsg);

}

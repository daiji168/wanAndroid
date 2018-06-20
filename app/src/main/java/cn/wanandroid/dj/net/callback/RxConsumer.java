package cn.wanandroid.dj.net.callback;

import cn.wanandroid.dj.bean.ResponModel;
import cn.wanandroid.dj.net.NetConfig;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * author: 康栋普
 * date: 2018/3/7
 */

public abstract class RxConsumer<T> implements Consumer<ResponModel<T>> {

    @Override
    public void accept(@NonNull ResponModel<T> responModel) throws Exception {
        if (responModel.getErrorCode() == NetConfig.REQUEST_SUCCESS){
            onSuccess(responModel.getData());
        }else {
            onFail(responModel.getErrorMsg());
        }
    }

    protected abstract void onFail(String errorMsg);

    protected abstract void onSuccess(T data);
}
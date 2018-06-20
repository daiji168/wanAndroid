package cn.wanandroid.dj.net.callback;


import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import cn.wanandroid.dj.R;
import cn.wanandroid.dj.bean.ResponModel;
import cn.wanandroid.dj.net.NetConfig;
import cn.wanandroid.dj.utils.ToastUtil;
import io.reactivex.observers.DisposableObserver;

/**
 * Observer基类
 */

public abstract class RxBaseObserver<T> extends DisposableObserver<ResponModel<T>> {

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onError(Throwable e) {
        /**
         * 处理异常
         */
        dealException(e);
    }

    @Override
    public void onComplete() {
    }

    /**
     * 处理异常错误
     *
     * @param t
     */
    void dealException(Throwable t) {
        Logger.e(t.getMessage());

        if (t instanceof ConnectException || t instanceof UnknownHostException) {
            //连接错误
            onException(NetConfig.CONNECT_ERROR);
        } else if (t instanceof InterruptedException) {
            //连接超时
            onException(NetConfig.CONNECT_TIMEOUT);
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {
            //解析错误
            onException(NetConfig.PARSE_ERROR);
        } else if (t instanceof SocketTimeoutException) {
            //请求超时
            onException(NetConfig.REQUEST_TIMEOUT);
        } else if (t instanceof UnknownError) {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR);
        } else {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR);
        }
    }


    void onException(int errorCode) {
        switch (errorCode) {
            case NetConfig.CONNECT_ERROR:
                ToastUtil.showShort(R.string.connect_error);
                break;
            case NetConfig.CONNECT_TIMEOUT:
                ToastUtil.showShort(R.string.connect_timeout);
                break;
            case NetConfig.PARSE_ERROR:
                ToastUtil.showShort(R.string.parse_error);
                break;
            case NetConfig.REQUEST_TIMEOUT:
                ToastUtil.showShort(R.string.request_timeout);
                break;
            case NetConfig.UNKNOWN_ERROR:
                ToastUtil.showShort(R.string.unknown_error);
                break;
        }
    }
}

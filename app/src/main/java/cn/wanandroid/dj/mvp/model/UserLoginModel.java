package cn.wanandroid.dj.mvp.model;

import cn.wanandroid.dj.bean.Login;
import cn.wanandroid.dj.net.RxSchedulers;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class UserLoginModel extends BaseModel{

    public void login(String userName, String passWord, RxObserver<Login> rxObserver){
        doRxRequest().login(userName,passWord).compose(RxSchedulers.io_main()).subscribe(rxObserver);
    }
}

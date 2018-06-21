package cn.wanandroid.dj.mvp.presenter;

import cn.wanandroid.dj.bean.Login;
import cn.wanandroid.dj.mvp.BasePresenter;
import cn.wanandroid.dj.mvp.model.UserLoginModel;
import cn.wanandroid.dj.mvp.view.IUserLoginView;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView> {
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     * @param mvpView
     */
    UserLoginModel userLoginModel=new UserLoginModel();
    public UserLoginPresenter(IUserLoginView mvpView) {
        super(mvpView);
    }

    public void login(String userName,String passWord){
        userLoginModel.login(userName, passWord, new RxObserver<Login>() {
            @Override
            protected void onSuccess(Login data) {
                mvpView.loginSuccess(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mvpView.loginFail(errorMsg);
            }
        });

    }

}

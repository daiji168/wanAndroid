package cn.wanandroid.dj.mvp.view;

import cn.wanandroid.dj.bean.Login;

/**
 * @author daiji
 * @date 2018/5/14
 */
public interface IUserLoginView {
    /**
     * 登陆成功
     */
    void loginSuccess(Login login);

    /**
     * 登陆失败
     */
    void loginFail(String message);
}

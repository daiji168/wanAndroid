package cn.wanandroid.dj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.bean.Login;
import cn.wanandroid.dj.mvp.presenter.UserLoginPresenter;
import cn.wanandroid.dj.mvp.view.IUserLoginView;
import cn.wanandroid.dj.utils.ToastUtil;

/**
 * 登录
 *
 * @author daiji
 * @date 2018/5/14
 */
public class LoginActivity extends BaseActivity<UserLoginPresenter> implements IUserLoginView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    private String etUser;
    private String etPwd;
    private static final int RESULT_OK=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new UserLoginPresenter(this);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initLayout() {
        initToolBar(toolbar,true,"登录");
    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        etUser = etUsername.getText().toString();
        etPwd = etPassword.getText().toString();
        if(!TextUtils.isEmpty(etUser)&&!TextUtils.isEmpty(etPwd)){
            presenter.login(etUser,etPwd);
        }else{
            ToastUtil.showShort("用户名和密码不能为空");
        }
    }

    @Override
    public void loginSuccess(Login login) {
        Intent intent=new Intent("update_collect");
        intent.putExtra("loginName",etUser);
        sendBroadcast(intent);
        setResult(RESULT_OK,intent);
        this.finish();
    }

    @Override
    public void loginFail(String message) {
        ToastUtil.showShort(message);
    }
}

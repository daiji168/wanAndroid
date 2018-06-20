package cn.wanandroid.dj.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.activity.CollectListActivity;
import cn.wanandroid.dj.activity.LoginActivity;
import cn.wanandroid.dj.constants.Constants;
import cn.wanandroid.dj.utils.PreUtils;

/**
 * @author daiji
 * @date 2018/6/11
 */
public class MeFragment extends BaseFragment {
    @BindView(R.id.tv_login_name)
    TextView tv_name;
    private String loginName;

    private static final int LOGIN=100;
    private static final int RESULT_OK=200;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initLayout(View view) {
        loginName = PreUtils.getString(Constants.sp_login);
        if(!TextUtils.isEmpty(loginName)){
            tv_name.setText(loginName);
        }
    }

    @Override
    public void initListener(View view) {

    }

    @OnClick({R.id.tv_collect, R.id.tv_setting,R.id.tv_login_name,R.id.exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_collect:
                startActivity(new Intent(getActivity(), CollectListActivity.class));
                break;
            case R.id.tv_setting:
                break;
            case R.id.tv_login_name:
                if(TextUtils.isEmpty(loginName)){
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class),LOGIN);
                }
                break;
            case R.id.exit_login:
                exitLogin();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            String name = data.getStringExtra("loginName");
            tv_name.setText(name);
            PreUtils.put(Constants.sp_login,name);
        }
    }

    private void exitLogin(){
        PreUtils.remove(Constants.sp_login);
        PreUtils.remove("www.wanandroid.com");

        tv_name.setText("未登录");
        Intent intent=new Intent("update_collect");
        getActivity().sendBroadcast(intent);
    }
}

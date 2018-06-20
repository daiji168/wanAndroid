package cn.wanandroid.dj.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.wanandroid.dj.constants.Constants;
import cn.wanandroid.dj.mvp.BasePresenter;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 *
 *@author daiji
 *@date 2018/5/14
*/
public abstract class BaseActivity<T extends BasePresenter> extends SwipeBackActivity {
    public T presenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setContentLayout() != 0) {
            setContentView(setContentLayout());
        }
        bind = ButterKnife.bind(this);
        swipeBackEnable(true);
        init();
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }

    protected void swipeBackEnable(Boolean falg) {
        setSwipeBackEnable(falg);
    }

    protected abstract int setContentLayout();

    protected void translucentStatusBar() {
        //5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 初始化 Toolbar
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        if (!TextUtils.isEmpty(title)) {
            toolbar.setTitle(title);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.onDestroy();
        }
        bind.unbind();
    }

    /**
     * 初始化
     */
    public void init() {
        initLayout();
        initListener();
    }

    /**
     * 初始化布局
     */
    public abstract void initLayout();

    /**
     * 设置控件事件
     */
    public abstract void initListener();

    protected void setData(boolean isRefresh, List data, BaseQuickAdapter adapter) {
        setData(isRefresh,data,adapter,null);
    }

    /**
     * @param isRefresh  是否为下拉刷新
     * @param data   显示需要的数据
     * @param adapter 适配器
     * @param view  当数据为空时显示的view
     */
    protected void setData(boolean isRefresh, List data, BaseQuickAdapter adapter, View view) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            if(size>0) {
                if(view!=null)view.setVisibility(View.GONE);
                adapter.setNewData(data);
            }else{
                if(view!=null)view.setVisibility(View.VISIBLE);
            }
        } else {
            if (size > 0) {
                adapter.addData(data);
            }
        }
        if (size < Constants.defaultSize) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);
        } else {
            adapter.loadMoreComplete();
        }
    }

}

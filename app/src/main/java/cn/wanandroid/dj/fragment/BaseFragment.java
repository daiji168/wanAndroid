package cn.wanandroid.dj.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.wanandroid.dj.constants.Constants;
import cn.wanandroid.dj.mvp.BasePresenter;

/**
 *@author daiji
 *@date 2018/5/14
*/
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    public T presenter;
    private Unbinder bind;

    @Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(setLayoutId(),null);
        bind = ButterKnife.bind(this, view);
        init(view);
		return view;
	}

	public abstract int setLayoutId();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void setTranslucentStatus() {
		if (Build.VERSION.SDK_INT >= 21) {
			View decorView = getActivity().getWindow().getDecorView();
			int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
			decorView.setSystemUiVisibility(option);
			getActivity().getWindow().setNavigationBarColor(Color.TRANSPARENT);
			getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
		}
	}

	/** 初始化 Toolbar */
	public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
		if(!TextUtils.isEmpty(title)){
		    toolbar.setTitle(title);
        }
		setHasOptionsMenu(true);
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				getActivity().onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 初始化
	 */
	public void init(View view){
		initLayout(view);
		initListener(view);
	}
	
	/**
	 * 初始化布局
	 */
	public abstract void initLayout(View view);
	
	/**
	 * 设置控件事件
	 */
	public abstract void initListener(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.onDestroy();
        }
    }

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		bind.unbind();
	}

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

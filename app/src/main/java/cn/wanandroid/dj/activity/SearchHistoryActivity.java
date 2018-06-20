package cn.wanandroid.dj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.adapter.TagAdapter;
import cn.wanandroid.dj.bean.Hot;
import cn.wanandroid.dj.mvp.presenter.SearchHistoryPresenter;
import cn.wanandroid.dj.mvp.view.IHotKeyView;
import cn.wanandroid.dj.view.FlowLayout;
import cn.wanandroid.dj.view.TagFlowLayout;

public class SearchHistoryActivity extends BaseActivity<SearchHistoryPresenter> implements IHotKeyView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tagFlowLayout)
    TagFlowLayout tagFlowLayout;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchHistoryPresenter(this);
        presenter.hotkeys();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_search_history;
    }

    @Override
    public void initLayout() {
        initToolBar(toolbar,true,"");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void hotKeys(final List<Hot> datas) {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        TagAdapter<Hot> tagAdapter = new TagAdapter<Hot>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, Hot children) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tagflow_textview, parent, false);
                tv.setText(children.getName());
                return tv;
            }
        };
        tagFlowLayout.setAdapter(tagAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                start(datas.get(position).getName());
                mSearchView.setQueryHint(datas.get(position).getName());
                mSearchView.clearFocus();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        mSearchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        mSearchView.setMaxWidth(1920);
        mSearchView.setIconified(false);
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                SearchHistoryActivity.this.finish();
                return true;
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                start(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void start(String key){
        Intent intent=new Intent(SearchHistoryActivity.this,SearchActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);
    }
}

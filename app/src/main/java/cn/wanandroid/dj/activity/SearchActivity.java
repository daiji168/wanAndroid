package cn.wanandroid.dj.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.adapter.ArticleListAdapter;
import cn.wanandroid.dj.bean.Article;
import cn.wanandroid.dj.mvp.presenter.SearchPresenter;
import cn.wanandroid.dj.mvp.view.ISearchView;
import cn.wanandroid.dj.view.DividerItemDecoration;

/**
 *搜索
 *@author daiji
 *@date 2018/6/6
*/
public class SearchActivity extends BaseActivity<SearchPresenter> implements ISearchView,BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.search_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private int page=0;
    private String key;
    private ArticleListAdapter articleListAdapter;
    private SearchView mSearchView;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initLayout() {
        initToolBar(toolbar,true,"");
        presenter=new SearchPresenter(this);

        Bundle extras = getIntent().getExtras();
        if(extras!=null&&extras.containsKey("key")){
            key = getIntent().getExtras().getString("key");
            presenter.search(page, key);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL, 5));

        articleListAdapter = new ArticleListAdapter();
        articleListAdapter.setOnLoadMoreListener(this);
        recyclerView.setAdapter(articleListAdapter);
    }

    @Override
    public void initListener() {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Article article = articleListAdapter.getData().get(position);
                String link = article.getLink();
                WebviewActivity.startatcitity(SearchActivity.this,link);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()){
                    case R.id.img_collect:
                        break;
                }
            }
        });

    }

    @Override
    public void articleLists(List<Article> data) {
        setData(page==0?true:false,data,articleListAdapter);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        presenter.search(page,key);
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
                SearchActivity.this.finish();
                return true;
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                key=query;
                page=0;
                presenter.search(page,query);
                mSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setQueryHint(key);
        mSearchView.clearFocus();
        return super.onCreateOptionsMenu(menu);
    }
}

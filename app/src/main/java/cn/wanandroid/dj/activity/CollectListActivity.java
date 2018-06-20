package cn.wanandroid.dj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.adapter.ArticleListAdapter;
import cn.wanandroid.dj.bean.Article;
import cn.wanandroid.dj.mvp.presenter.CollectPresenter;
import cn.wanandroid.dj.mvp.view.ICollectView;
import cn.wanandroid.dj.utils.ToastUtil;
import cn.wanandroid.dj.view.DividerItemDecoration;

/**
 * 收藏列表
 *
 * @author daiji
 * @date 2018/6/11
 */
public class CollectListActivity extends BaseActivity<CollectPresenter> implements ICollectView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ArticleListAdapter articleListAdapter;
    private int page=0;
    private int mposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setTitle("");
        initToolBar(toolbar,true,"");
        presenter=new CollectPresenter(this);
        presenter.collectLists(page);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_collect_list;
    }

    @Override
    public void initLayout() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL, 5));

        swipeRefreshLayout.setOnRefreshListener(this);

        articleListAdapter = new ArticleListAdapter(0);
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
                WebviewActivity.startatcitity(CollectListActivity.this,link);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                mposition=position;
                Article article = articleListAdapter.getData().get(position);
                switch (view.getId()){
                    case R.id.img_collect:
                        presenter.cancelCollect(article.getId(),article.getOriginId());
                        break;
                }
            }
        });
    }

    @Override
    public void collectLists(List<Article> datas) {
        swipeRefreshLayout.setRefreshing(false);
        setData(page == 0 ? true : false, datas, articleListAdapter);
    }

    @Override
    public void cancelCollect(int code) {
        if(code==0){
            ToastUtil.showShort("已取消收藏");
            articleListAdapter.remove(mposition);
            articleListAdapter.notifyDataSetChanged();
        }else{
            ToastUtil.showShort("取消收藏失败");
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        page=0;
        presenter.collectLists(page);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.collectLists(page);
            }
        }, 500);
    }

    @Override
    protected void onDestroy() {
        Intent intent=new Intent("update_collect");
        sendBroadcast(intent);
        super.onDestroy();
    }
}

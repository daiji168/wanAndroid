package cn.wanandroid.dj.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.activity.WebviewActivity;
import cn.wanandroid.dj.adapter.ArticleListAdapter;
import cn.wanandroid.dj.bean.Article;
import cn.wanandroid.dj.bean.BannerModle;
import cn.wanandroid.dj.mvp.presenter.HomePresenter;
import cn.wanandroid.dj.mvp.view.IArticleListView;
import cn.wanandroid.dj.utils.ToastUtil;
import cn.wanandroid.dj.view.DividerItemDecoration;

/**
 * @author daiji
 * @date 2018/6/7
 */
public class ArticleTypeFragment extends BaseFragment<HomePresenter> implements IArticleListView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ArticleListAdapter articleListAdapter;
    private int page=0;
    private int cid;
    private ImageView imageView;
    private Article articledata;

    public static ArticleTypeFragment newInstance(int cid) {
        ArticleTypeFragment typeFragment = new ArticleTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        typeFragment.setArguments(bundle);
        return typeFragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_articletype;
    }

    @Override
    public void initLayout(View view) {
        cid=getArguments().getInt("cid");
        presenter=new HomePresenter(this);
        presenter.getArticleLists(page,cid);
        swipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL, 5));

        articleListAdapter = new ArticleListAdapter();
        articleListAdapter.setOnLoadMoreListener(this);
        recyclerView.setAdapter(articleListAdapter);
    }

    @Override
    public void initListener(View view) {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Article article = articleListAdapter.getData().get(position);
                String link = article.getLink();
                WebviewActivity.startatcitity(getActivity(),link);
            }
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                imageView = (ImageView) view;
                articledata = articleListAdapter.getData().get(position);
                switch (view.getId()){
                    case R.id.img_collect:
                        if(articledata.isCollect())presenter.cancelCollect(articledata.getId());
                        else presenter.addCollect(articledata.getId());
                        break;
                }
            }
        });
    }

    @Override
    public void articleListDatas(List<Article> data) {
        swipeRefreshLayout.setRefreshing(false);
        setData(page==0?true:false,data,articleListAdapter);
    }

    @Override
    public void addCollect(int code) {
        if(code==0){
            ToastUtil.showShort("收藏成功");
            imageView.setImageResource(R.drawable.collected);
            articledata.setCollect(true);
        }else{
            ToastUtil.showShort("添加收藏失败");
        }
    }

    @Override
    public void cancelCollect(int code) {
        if(code==0){
            ToastUtil.showShort("已取消收藏");
            imageView.setImageResource(R.drawable.uncollect);
            articledata.setCollect(false);
        }else{
            ToastUtil.showShort("取消收藏失败");
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        page=0;
        presenter.getArticleLists(page,cid);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getArticleLists(page,cid);
            }
        }, 500);
    }

    @Override
    public void getBannerDatas(List<BannerModle> data) {}
}

package cn.wanandroid.dj.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
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
import cn.wanandroid.dj.view.GlideImageLoader;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class HomeFragement extends BaseFragment<HomePresenter> implements IArticleListView,SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    Banner banner;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int page = 0;
    private ArticleListAdapter articleListAdapter;
    private ImageView imageView;
    private Article articledata;
    private MyBroadcastReceiver receiver;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initLayout(View view) {
        presenter = new HomePresenter(this);
        presenter.getBannerDatas();
        presenter.getListDatas(page);

        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("update_collect");
        getActivity().registerReceiver(receiver, filter);


        swipeRefreshLayout.setOnRefreshListener(this);

        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.home_banner,null);
        banner = inflate.findViewById(R.id.banner);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL, 5));

        articleListAdapter = new ArticleListAdapter();
        articleListAdapter.setOnLoadMoreListener(this);
        recyclerView.setAdapter(articleListAdapter);
        articleListAdapter.addHeaderView(inflate);
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
    public void getBannerDatas(final List<BannerModle> data) {
        List<String> titles = new ArrayList<>();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            BannerModle modle = data.get(i);
            titles.add(modle.getDesc());
            images.add(modle.getImagePath());
        }
        banner.setImages(images)
                .setBannerTitles(titles)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerModle modle = data.get(position);
                String url = modle.getUrl();
                WebviewActivity.startatcitity(getActivity(),url);
            }
        });
    }

    @Override
    public void articleListDatas(List<Article> data) {
        swipeRefreshLayout.setRefreshing(false);
        setData(page == 0 ? true : false, data, articleListAdapter);
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
        presenter.getListDatas(page);
    }
    @Override
    public void onLoadMoreRequested() {
        page++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getListDatas(page);
            }
        }, 500);
    }


    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("update_collect")){
                page=0;
                presenter.getListDatas(page);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}

package cn.wanandroid.dj.mvp.presenter;

import java.util.List;

import cn.wanandroid.dj.bean.ArticleModel;
import cn.wanandroid.dj.bean.BannerModle;
import cn.wanandroid.dj.mvp.BasePresenter;
import cn.wanandroid.dj.mvp.model.HomeModel;
import cn.wanandroid.dj.mvp.view.IArticleListView;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class HomePresenter extends BasePresenter<IArticleListView> {
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mvpView
     */
    HomeModel homeModel;
    public HomePresenter(IArticleListView mvpView) {
        super(mvpView);
        homeModel=new HomeModel();
    }

    public void getBannerDatas(){
        homeModel.getBannerDatas(new RxObserver<List<BannerModle>>() {
            @Override
            protected void onSuccess(List<BannerModle> data) {
                mvpView.getBannerDatas(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    public void getListDatas(int page){
        homeModel.getListDatas(page, new RxObserver<ArticleModel>() {
            @Override
            protected void onSuccess(ArticleModel data) {
                mvpView.articleListDatas(data.getDatas());
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    public void getArticleLists(int page,int cid){
        homeModel.getListDatas(page, cid, new RxObserver<ArticleModel>() {
            @Override
            protected void onSuccess(ArticleModel data) {
                mvpView.articleListDatas(data.getDatas());
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    public void addCollect(int id){
        homeModel.collect(id, new RxObserver<String>() {
            @Override
            protected void onSuccess(String data) {
                mvpView.addCollect(0);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mvpView.addCollect(errorCode);
            }
        });
    }
    public void cancelCollect(int id){
//        homeModel.cancelCollect(id,mvpView);
        homeModel.cancelCollect(id, new RxObserver<String>() {
            @Override
            protected void onSuccess(String data) {
                mvpView.cancelCollect(0);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mvpView.cancelCollect(errorCode);
            }
        });
    }

}

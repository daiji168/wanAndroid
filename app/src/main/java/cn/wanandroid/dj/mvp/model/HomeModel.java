package cn.wanandroid.dj.mvp.model;

import java.util.List;

import cn.wanandroid.dj.bean.ArticleModel;
import cn.wanandroid.dj.bean.BannerModle;
import cn.wanandroid.dj.net.RxSchedulers;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 */
public class HomeModel extends BaseModel {

    /**
     * banner图数据
     */
    public void getBannerDatas(RxObserver<List<BannerModle>> observer){
        doRxRequest()
        .getBanner()
        .compose(RxSchedulers.io_main())
        .subscribe(observer);
    }

    /**
     * 首页文章列表
     * @param page
     */
    public void getListDatas(int page, RxObserver<ArticleModel> listRxObserver){
        doRxRequest().getArticleList(page).compose(RxSchedulers.io_main()).subscribe(listRxObserver);

    }

    /**
     * 体系下的文章列表
     * @param page
     */
    public void getListDatas(int page, int cid, RxObserver<ArticleModel> observer){
       doRxRequest().getTreeList(page,cid).compose(RxSchedulers.io_main()).subscribe(observer);

    }

    /**
     * 取消收藏
     * @param id
     */
    public void cancelCollect(int id,RxObserver<String> observer){
        doRxRequest().cancelCollectArticle(id).compose(RxSchedulers.io_main()).subscribe(observer);
    }

    /**
     * 添加收藏
     * @param id
     */
    public void collect(int id,RxObserver<String> observer) {
        doRxRequest().collectArticle(id).compose(RxSchedulers.io_main()).subscribe(observer);
    }
}

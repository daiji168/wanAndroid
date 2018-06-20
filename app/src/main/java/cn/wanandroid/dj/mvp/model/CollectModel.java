package cn.wanandroid.dj.mvp.model;

import cn.wanandroid.dj.bean.ArticleModel;
import cn.wanandroid.dj.net.RxSchedulers;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/11
 */
public class CollectModel extends BaseModel {
    /**
     * 收藏列表
     * @param page
     * @param observer
     */
    public void collectLists(int page, RxObserver<ArticleModel> observer){
        doRxRequest().getCollectArticleList(page).compose(RxSchedulers.io_main()).subscribe(observer);
    }

    /**
     * 从收藏列表中删除收藏的文章
     * @param id
     * @param originId
     * @param observer
     */
    public void cancelCollect(int id,int originId,RxObserver<String> observer){
        doRxRequest().deleteCollectArticle(id,originId).compose(RxSchedulers.io_main()).subscribe(observer);
    }
}

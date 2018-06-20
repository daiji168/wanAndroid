package cn.wanandroid.dj.mvp.model;

import cn.wanandroid.dj.bean.ArticleModel;
import cn.wanandroid.dj.net.RxSchedulers;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/6
 */
public class SearchModel extends BaseModel {

    /**
     * 搜素
     * @param page
     * @param key
     * @param observer
     */
    public void search(int page, String key, RxObserver<ArticleModel> observer){
        doRxRequest().search(page,key).compose(RxSchedulers.io_main()).subscribe(observer);

    }
}

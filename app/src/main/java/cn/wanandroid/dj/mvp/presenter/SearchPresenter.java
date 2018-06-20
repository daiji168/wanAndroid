package cn.wanandroid.dj.mvp.presenter;

import cn.wanandroid.dj.bean.ArticleModel;
import cn.wanandroid.dj.mvp.BasePresenter;
import cn.wanandroid.dj.mvp.model.SearchModel;
import cn.wanandroid.dj.mvp.view.ISearchView;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/6
 */
public class SearchPresenter extends BasePresenter<ISearchView> {
    private SearchModel searchModel;
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mvpView
     */
    public SearchPresenter(ISearchView mvpView) {
        super(mvpView);
        searchModel=new SearchModel();
    }

    public void search(int page,String key){
        searchModel.search(page, key, new RxObserver<ArticleModel>() {
            @Override
            protected void onSuccess(ArticleModel data) {
                mvpView.articleLists(data.getDatas());
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        });
    }
}

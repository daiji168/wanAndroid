package cn.wanandroid.dj.mvp.presenter;

import cn.wanandroid.dj.bean.ArticleModel;
import cn.wanandroid.dj.mvp.BasePresenter;
import cn.wanandroid.dj.mvp.model.CollectModel;
import cn.wanandroid.dj.mvp.view.ICollectView;
import cn.wanandroid.dj.net.callback.RxObserver;
import cn.wanandroid.dj.utils.ToastUtil;

/**
 * @author daiji
 * @date 2018/6/11
 */
public class CollectPresenter extends BasePresenter<ICollectView> {

    private final CollectModel collectModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mvpView
     */
    public CollectPresenter(ICollectView mvpView) {
        super(mvpView);
        collectModel = new CollectModel();
    }

    public void collectLists(int page){
        collectModel.collectLists(page, new RxObserver<ArticleModel>() {
            @Override
            protected void onSuccess(ArticleModel data) {
                mvpView.collectLists(data.getDatas());
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                ToastUtil.showShort(errorMsg);
            }
        });
    }

    public void cancelCollect(int id,int originId){
        collectModel.cancelCollect(id, originId, new RxObserver<String>() {
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

package cn.wanandroid.dj.mvp.presenter;

import java.util.List;

import cn.wanandroid.dj.bean.Hot;
import cn.wanandroid.dj.mvp.BasePresenter;
import cn.wanandroid.dj.mvp.model.WebSiteModel;
import cn.wanandroid.dj.mvp.view.IHotKeyView;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/8
 */
public class SearchHistoryPresenter extends BasePresenter<IHotKeyView> {

    private final WebSiteModel webSiteModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mvpView
     */
    public SearchHistoryPresenter(IHotKeyView mvpView) {
        super(mvpView);
        webSiteModel = new WebSiteModel();
    }
    public void hotkeys(){
        webSiteModel.hotkeys(new RxObserver<List<Hot>>() {
            @Override
            protected void onSuccess(List<Hot> data) {
                mvpView.hotKeys(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        });
    }
}

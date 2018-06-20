package cn.wanandroid.dj.mvp.presenter;

import java.util.List;

import cn.wanandroid.dj.bean.Hot;
import cn.wanandroid.dj.mvp.BasePresenter;
import cn.wanandroid.dj.mvp.model.WebSiteModel;
import cn.wanandroid.dj.mvp.view.IWebsiteView;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/7
 */
public class WebsitePresenter extends BasePresenter<IWebsiteView> {

    private final WebSiteModel webSiteModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mvpView
     */
    public WebsitePresenter(IWebsiteView mvpView) {
        super(mvpView);
        webSiteModel = new WebSiteModel();
    }

    public void getDatas(){
        webSiteModel.getWebsite(new RxObserver<List<Hot>>() {
            @Override
            protected void onSuccess(List<Hot> data) {
                mvpView.websiteLists(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        });
    }
}

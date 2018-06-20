package cn.wanandroid.dj.mvp.model;

import java.util.List;

import cn.wanandroid.dj.bean.Hot;
import cn.wanandroid.dj.net.RxSchedulers;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/7
 */
public class WebSiteModel extends BaseModel {
    /**
     * 常用网站
     * @param observer
     */
    public void getWebsite(RxObserver<List<Hot>> observer){
        doRxRequest().getWebsite().compose(RxSchedulers.io_main()).subscribe(observer);
    }

    /**
     * 搜素热词
     * @param observer
     */
    public void hotkeys(RxObserver<List<Hot>> observer){
        doRxRequest().getHotKeyword().compose(RxSchedulers.io_main()).subscribe(observer);
    }
}

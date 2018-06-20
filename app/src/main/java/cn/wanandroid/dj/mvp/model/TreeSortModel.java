package cn.wanandroid.dj.mvp.model;

import java.util.List;

import cn.wanandroid.dj.bean.TreeModel;
import cn.wanandroid.dj.net.RxSchedulers;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/6
 */
public class TreeSortModel extends BaseModel {

    public void getDatas(RxObserver<List<TreeModel>> observer){
        doRxRequest().getTree().compose(RxSchedulers.io_main()).subscribe(observer);
    }
}

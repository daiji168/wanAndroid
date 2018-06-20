package cn.wanandroid.dj.mvp.presenter;

import java.util.List;

import cn.wanandroid.dj.bean.TreeModel;
import cn.wanandroid.dj.mvp.BasePresenter;
import cn.wanandroid.dj.mvp.model.TreeSortModel;
import cn.wanandroid.dj.mvp.view.ITreeView;
import cn.wanandroid.dj.net.callback.RxObserver;

/**
 * @author daiji
 * @date 2018/6/6
 */
public class TreePresenter extends BasePresenter<ITreeView> {
    private TreeSortModel treeModel;
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mvpView
     */
    public TreePresenter(ITreeView mvpView) {
        super(mvpView);
        treeModel=new TreeSortModel();
    }

    public void getDatas(){
        treeModel.getDatas(new RxObserver<List<TreeModel>>() {
            @Override
            protected void onSuccess(List<TreeModel> data) {
                mvpView.getListDatas(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        });
    }

}

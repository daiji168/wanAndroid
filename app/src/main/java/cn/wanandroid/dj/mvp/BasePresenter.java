package cn.wanandroid.dj.mvp;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class BasePresenter<T> {

    protected T mvpView;
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     */
    public BasePresenter(T mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 因为presenter层持有view层，所以，提供一个方法，在view层不使用的时候将对象释放
     */
    public void onDestroy() {
        mvpView = null;
    }
}

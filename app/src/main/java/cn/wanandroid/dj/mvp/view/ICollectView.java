package cn.wanandroid.dj.mvp.view;

import java.util.List;

import cn.wanandroid.dj.bean.Article;

/**
 * @author daiji
 * @date 2018/6/11
 */
public interface ICollectView {
    void collectLists(List<Article> datas);

    void cancelCollect(int code);
}

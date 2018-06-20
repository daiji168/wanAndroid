package cn.wanandroid.dj.mvp.view;

import java.util.List;

import cn.wanandroid.dj.bean.Article;

/**
 * @author daiji
 * @date 2018/6/7
 */
public interface IArticleListView extends IHomeView{
    void articleListDatas(List<Article> data);

    void addCollect(int code);

    void cancelCollect(int code);
}

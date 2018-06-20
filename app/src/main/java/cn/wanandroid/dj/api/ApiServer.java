package cn.wanandroid.dj.api;

import java.util.List;

import cn.wanandroid.dj.bean.ArticleModel;
import cn.wanandroid.dj.bean.BannerModle;
import cn.wanandroid.dj.bean.Hot;
import cn.wanandroid.dj.bean.Login;
import cn.wanandroid.dj.bean.ResponModel;
import cn.wanandroid.dj.bean.TreeModel;
import cn.wanandroid.dj.constants.HttpConstants;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiServer {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstants.LOGIN)
    Observable<ResponModel<Login>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 重复密码
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstants.REGISTER)
    Observable<ResponModel<String>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    /**
     * 广告
     *
     * @return
     */
    @GET(HttpConstants.BANNER)
    Observable<ResponModel<List<BannerModle>>> getBanner();

    /**
     * 首页文章列表
     *
     * @return
     */
    @GET(HttpConstants.ARTICLELIST)
    Observable<ResponModel<ArticleModel>> getArticleList(@Path("page") int page);

    /**
     * 收藏文章
     *
     * @param id
     * @return
     */
    @POST(HttpConstants.COLLECT)
    Observable<ResponModel<String>> collectArticle(@Path("id") int id);

    /**
     * 取消收藏文章
     *
     * @param id
     * @return
     */

    @POST(HttpConstants.CANCELCOLLECT)
    Observable<ResponModel<String>> cancelCollectArticle(@Path("id") int id);


//    /**
//     * 收藏站内文章
//     *
//     * @param id
//     * @return
//     */
//    @POST(HttpConstants.COLLECT_INSIDE_ARTICLE)
//    Observable<BaseBean<String>> collectInsideArticle(@Path("id") int id);
//

    /**
     * 知识体系分类
     *
     * @return
     */
    @GET(HttpConstants.TREE)
    Observable<ResponModel<List<TreeModel>>> getTree();

    /**
     * 知识体系列表
     *
     * @param cid
     * @param page
     * @return
     */
    @GET(HttpConstants.ARTICLELISTS)
    Observable<ResponModel<ArticleModel>> getTreeList(@Path("page") int page, @Query("cid") int cid);


    /**
     * 收藏的文章列表
     *
     * @param page
     * @return
     */
    @GET(HttpConstants.COLLECTLIST)
    Observable<ResponModel<ArticleModel>> getCollectArticleList(@Path("page") int page);

    /**
     * 删除收藏的文章
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstants.CANCELCOLLECTS)
    Observable<ResponModel<String>> deleteCollectArticle(@Path("id") int id, @Field("originId") int originId);

    /**
     * 搜索文章
     *
     * @param page
     * @param keyword
     * @return
     */
    @FormUrlEncoded
    @POST(HttpConstants.SEARCH)
    Observable<ResponModel<ArticleModel>> search(@Path("page") int page, @Field("k") String keyword);

    /**
     * 搜索热词
     *
     * @return
     */
    @GET(HttpConstants.HOTKEY)
    Observable<ResponModel<List<Hot>>> getHotKeyword();
//
    /**
     * 常用网站
     *
     * @return
     */
    @GET(HttpConstants.WEBSITE)
    Observable<ResponModel<List<Hot>>> getWebsite();

}

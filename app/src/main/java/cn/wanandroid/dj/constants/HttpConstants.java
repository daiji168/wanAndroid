package cn.wanandroid.dj.constants;

/**
 * @author daiji
 * @date 2018/6/14
 */
public class HttpConstants {

    public static final String baseUrl="http://www.wanandroid.com/";

    /**轮播图*/
    public static final String BANNER="banner/json";
    /**文章列表*/
    public static final String ARTICLELIST="article/list/{page}/json";
    /**搜索*/
    public static final String SEARCH="article/query/{page}/json";
    /**体系*/
    public static final String TREE="tree/json";
    /**登录*/
    public static final String LOGIN="user/login";
    /**体系下文章列表*/
    public static final String ARTICLELISTS="article/list/{page}/json?";
    /**常用网站*/
    public static final String WEBSITE="friend/json";
    /**搜索热词*/
    public static final String HOTKEY="hotkey/json";
    /**收藏文章*/
    public static final String COLLECT="lg/collect/{id}/json";
    /**取消收藏文章*/
    public static final String CANCELCOLLECT="lg/uncollect_originId/{id}/json";
    /**取消收藏文章*/
    public static final String CANCELCOLLECTS="lg/uncollect/{id}/json";
    /**收藏的文章列表*/
    public static final String COLLECTLIST="lg/collect/list/{page}/json";
    /***注册*/
    public static final String REGISTER = "user/register";
}

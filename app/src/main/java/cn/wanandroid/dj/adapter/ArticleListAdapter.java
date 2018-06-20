package cn.wanandroid.dj.adapter;

import android.text.Html;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.wanandroid.dj.R;
import cn.wanandroid.dj.bean.Article;

/**
 * @author daiji
 * @date 2018/6/5
 */
public class ArticleListAdapter extends BaseQuickAdapter<Article,BaseViewHolder> {
    private int type=-1;
    public ArticleListAdapter() {
        super(R.layout.article_item);
    }

    public ArticleListAdapter(int mtype){
        super(R.layout.article_item);
        type=mtype;
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.article_author,item.getAuthor());
        helper.setText(R.id.article_title, Html.fromHtml(item.getTitle()));
        helper.setText(R.id.article_time,item.getNiceDate());

        helper.setText(R.id.sort_name,item.getChapterName());

        ImageView imageView=helper.getView(R.id.img_collect);
        if(type==0){
            imageView.setImageResource(R.drawable.collected);
        }else{
            if(item.isCollect())imageView.setImageResource(R.drawable.collected);
            else imageView.setImageResource(R.drawable.uncollect);
        }

        helper.addOnClickListener(R.id.img_collect);
    }
}

package cn.wanandroid.dj.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.adapter.TagAdapter;
import cn.wanandroid.dj.bean.Hot;
import cn.wanandroid.dj.mvp.presenter.WebsitePresenter;
import cn.wanandroid.dj.mvp.view.IWebsiteView;
import cn.wanandroid.dj.view.FlowLayout;
import cn.wanandroid.dj.view.TagFlowLayout;

/**
 * 常用网站
 *
 * @author daiji
 * @date 2018/6/7
 */
public class WebsiteActivity extends BaseActivity<WebsitePresenter> implements IWebsiteView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tagFlowLayout)
    TagFlowLayout tagFlowLayout;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_website;
    }

    @Override
    public void initLayout() {
        toolbar.setTitle("");
        initToolBar(toolbar,true,"");

        presenter=new WebsitePresenter(this);
        presenter.getDatas();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void websiteLists(final List<Hot> datas) {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        TagAdapter<Hot> tagAdapter = new TagAdapter<Hot>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, Hot children) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tagflow_textview, parent, false);
                tv.setText(children.getName());

                Random random = new Random();
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                tv.setTextColor(Color.rgb(r,g,b));
                return tv;
            }
        };
        tagFlowLayout.setAdapter(tagAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                WebviewActivity.startatcitity(WebsiteActivity.this,datas.get(position).getLink());
                return true;
            }
        });
    }
}

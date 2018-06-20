package cn.wanandroid.dj.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.bean.Tree;
import cn.wanandroid.dj.fragment.ArticleTypeFragment;

public class ArticleTypeActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    private String[] mTitles;
    private ArrayList<Tree> data;


    @Override
    protected int setContentLayout() {
        return R.layout.activity_article_type;
    }

    @Override
    public void initLayout() {
        toolbar.setTitle("");
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        title.setText(name);
        initToolBar(toolbar,true,"");

        data = (ArrayList<Tree>) extras.getSerializable("data");
        mTitles = new String[data.size()];

        for (int i = 0; i < data.size(); i++) {
            mTitles[i]= data.get(i).getName();
        }

        viewpage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        slidingTabs.setViewPager(viewpage);
    }

    @Override
    public void initListener() {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return ArticleTypeFragment.newInstance(data.get(position).getId());
        }
    }
}

package cn.wanandroid.dj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.fragment.HomeFragement;
import cn.wanandroid.dj.fragment.MeFragment;
import cn.wanandroid.dj.fragment.TreeFragment;

/**
 * 首页
 *
 * @author daiji
 * @date 2018/5/14
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    private Fragment currentFragment;
    private Fragment homeFragment;
    private Fragment treeFragment;
    private Fragment meFragment;
    private Fragment[] fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    showFragment(1);
                    return true;
                case R.id.navigation_me:
                    showFragment(2);
                    return true;
                default:
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        swipeBackEnable(false);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initLayout() {
        initToolBar(toolbar,false,null);
        initFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, homeFragment, "mainfragment").show(homeFragment).commit();
        currentFragment = homeFragment;
    }

    private void initFragment() {
        homeFragment = new HomeFragement();
        treeFragment = new TreeFragment();
        meFragment = new MeFragment();
        fragments = new Fragment[]{homeFragment, treeFragment,meFragment};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            startActivity(new Intent(this, SearchHistoryActivity.class));
        }
        if (item.getItemId() == R.id.hot) {
            startActivity(new Intent(this, WebsiteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initListener() {

    }

    /**
     * 切换
     * @param index
     */
    public void showFragment(int index) {
        if (index == 0) {
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragments[0], "mainfragment");
        } else if (index == 1) {
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragments[1], "treeFragment");
        } else if (index == 2) {
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragments[2], "meFragment");
        } else if (index == 3) {
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragments[3], "paimaifragment");
        } else if (index == 4) {
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragments[4], "mefragment");
        }
    }

    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment, String tag) {
        if (currentFragment == fragment) {
            return;
        }
        if (!fragment.isAdded()) {
            // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.fragment, fragment, tag).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }
}

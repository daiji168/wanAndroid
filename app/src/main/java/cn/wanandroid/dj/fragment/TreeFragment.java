package cn.wanandroid.dj.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.wanandroid.dj.R;
import cn.wanandroid.dj.activity.ArticleTypeActivity;
import cn.wanandroid.dj.adapter.TreeAdapter;
import cn.wanandroid.dj.bean.Tree;
import cn.wanandroid.dj.bean.TreeModel;
import cn.wanandroid.dj.mvp.presenter.TreePresenter;
import cn.wanandroid.dj.mvp.view.ITreeView;
import cn.wanandroid.dj.view.DividerItemDecoration;

/**
 * 知识体系
 * @author daiji
 * @date 2018/5/14
 */
public class TreeFragment extends BaseFragment<TreePresenter> implements ITreeView{
    @BindView(R.id.tree_recycleview)
    RecyclerView recyclerView;
    private TreeAdapter treeAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    public void initLayout(View view) {
        presenter=new TreePresenter(this);
        presenter.getDatas();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL, 5));

        treeAdapter = new TreeAdapter();
        recyclerView.setAdapter(treeAdapter);
    }

    @Override
    public void initListener(View view) {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                TreeModel item = treeAdapter.getData().get(position);
                ArrayList<Tree> children = (ArrayList<Tree>) item.getChildren();
                Intent intent=new Intent(getActivity(), ArticleTypeActivity.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("data",children);
                startActivity(intent);
            }
        });

    }

    @Override
    public void getListDatas(List<TreeModel> data) {
        setData(true,data,treeAdapter);
    }
}

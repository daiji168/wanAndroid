package cn.wanandroid.dj.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.wanandroid.dj.R;
import cn.wanandroid.dj.bean.Tree;
import cn.wanandroid.dj.bean.TreeModel;

/**
 * @author daiji
 * @date 2018/6/6
 */
public class TreeAdapter extends BaseQuickAdapter<TreeModel,BaseViewHolder> {

    public TreeAdapter() {
        super(R.layout.tree_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeModel item) {
        helper.setText(R.id.item_first,item.getName());
        StringBuffer sb = new StringBuffer();
        List<Tree> children = item.getChildren();

        for (Tree child : children) {
            sb.append(child.getName() + "     ");
        }
        helper.setText(R.id.item_second, sb.toString());
    }
}

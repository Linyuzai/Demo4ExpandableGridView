package com.linyuzai.expandablegridview.adapter;

import com.linyuzai.expandablegridview.ExpandableGridAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/21 0021.
 */
public abstract class SimpleExpandableGridAdapter<T> extends ExpandableGridAdapter {

    private List<List<T>> dataList;

    public SimpleExpandableGridAdapter(List<List<T>> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getGridGroupCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getGridChildCount(int gridGroupPosition) {
        List<T> children = dataList.get(gridGroupPosition);
        return children == null ? 0 : children.size();
    }

    public T getData(int gridGroupPosition, int gridChildPosition) {
        return dataList.get(gridGroupPosition).get(gridChildPosition);
    }
}

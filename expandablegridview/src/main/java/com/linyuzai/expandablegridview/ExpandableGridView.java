package com.linyuzai.expandablegridview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

/**
 * Created by Administrator on 2016/8/21 0021.
 */
public class ExpandableGridView extends ExpandableListView implements ExpandableListView.OnGroupClickListener {

    boolean isOverwriteOnMeasure = false;

    private boolean isGroupClickEnable = true;

    public ExpandableGridView(Context context) {
        super(context);
        init(context);
    }

    public ExpandableGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpandableGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandableGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setOnGroupClickListener(this);
    }

    @Deprecated
    @Override
    public void setAdapter(ListAdapter adapter) {
        throw new RuntimeException(
                "For ExpandableGridView, use setExpandableGridAdapter(ExpandableGridAdapter) instead of " +
                        "setAdapter(ListAdapter)");
    }

    @Deprecated
    @Override
    public void setAdapter(ExpandableListAdapter adapter) {
        throw new RuntimeException(
                "For ExpandableGridView, use setExpandableGridAdapter(ExpandableGridAdapter) instead of " +
                        "setAdapter(ExpandableListAdapter)");
    }

    public void setExpandableGridAdapter(ExpandableGridAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Deprecated
    @Override
    public ListAdapter getAdapter() {
        return super.getAdapter();
        /*throw new RuntimeException(
                "For ExpandableGridView, use getExpandableGridAdapter() instead of getAdapter()");*/
    }

    @Deprecated
    @Override
    public ExpandableListAdapter getExpandableListAdapter() {
        throw new RuntimeException(
                "For ExpandableGridView, use getExpandableGridAdapter() instead of getExpandableListAdapter()");
    }

    public ExpandableGridAdapter getExpandableGridAdapter() {
        return (ExpandableGridAdapter) super.getExpandableListAdapter();
    }

    public boolean isGroupClickEnable() {
        return isGroupClickEnable;
    }

    public void setGroupClickEnable(boolean groupClickEnable) {
        isGroupClickEnable = groupClickEnable;
    }

    public void setOnGridItemClickListener(OnGridItemClickListener listener) {
        getExpandableGridAdapter().listener = listener;
    }

    public OnGridItemClickListener getOnGridItemClickListener() {
        return getExpandableGridAdapter().listener;
    }

    public void expandAll() {
        expandAll(false);
    }

    public void expandAll(boolean animate) {
        if (getExpandableGridAdapter() == null) {
            return;
        }
        int count = getExpandableGridAdapter().getGroupCount();
        for (int groupPosition = 0; groupPosition < count; groupPosition++)
            expandGroup(groupPosition, animate);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = heightMeasureSpec;
        if (isOverwriteOnMeasure)
            expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return !isGroupClickEnable;
    }
}

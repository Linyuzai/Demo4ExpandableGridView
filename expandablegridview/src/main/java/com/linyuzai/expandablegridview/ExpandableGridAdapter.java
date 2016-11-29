package com.linyuzai.expandablegridview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;

/**
 * Created by Administrator on 2016/8/21 0021.
 */
public abstract class ExpandableGridAdapter extends BaseExpandableListAdapter {

    int horizontalSpacing;
    int verticalSpacing;
    OnGridItemClickListener listener;

    @Override
    public int getGroupCount() {
        return getGridGroupCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Deprecated
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Deprecated
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Deprecated
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Deprecated
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return getGridGroupView(groupPosition, isExpanded, convertView, parent);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GridAdapter gridAdapter;
        if (convertView == null) {
            convertView = new XGridView(parent.getContext());
            gridAdapter = new GridAdapter(this, groupPosition);
            convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));
            convertView.setVerticalScrollBarEnabled(false);
            ((XGridView) convertView).setHorizontalSpacing(horizontalSpacing);
            ((XGridView) convertView).setVerticalSpacing(verticalSpacing);
            ((XGridView) convertView).setAdapter(gridAdapter);
            ((XGridView) convertView).setSelector(new ColorDrawable(Color.TRANSPARENT));
        } else {
            gridAdapter = (GridAdapter) ((XGridView) convertView).getAdapter();
            gridAdapter.setGridGroupPosition(groupPosition);
            gridAdapter.notifyDataSetChanged();
        }
        ((XGridView) convertView).setNumColumns(getNumColumns(groupPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public abstract int getGridGroupCount();

    public abstract int getGridChildCount(int gridGroupPosition);

    public abstract View getGridGroupView(int gridGroupPosition, boolean isExpanded, View convertView, ViewGroup parent);

    public abstract View getGridChildView(int gridGroupPosition, int gridChildPosition, View convertView, ViewGroup parent);

    public abstract int getNumColumns(int gridGroupPosition);

    private class GridAdapter extends BaseAdapter {

        private ExpandableGridAdapter expandableGridAdapter;
        private int gridGroupPosition;

        GridAdapter(ExpandableGridAdapter expandableGridAdapter, int gridGroupPosition) {
            this.expandableGridAdapter = expandableGridAdapter;
            this.gridGroupPosition = gridGroupPosition;
        }

        public int getGridGroupPosition() {
            return gridGroupPosition;
        }

        void setGridGroupPosition(int gridGroupPosition) {
            this.gridGroupPosition = gridGroupPosition;
        }

        @Override
        public int getCount() {
            return expandableGridAdapter.getGridChildCount(gridGroupPosition);
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getGridChildView(gridGroupPosition, position, convertView, parent);
            convertView.setOnClickListener(new OnClickListenerImpl(position));
            return convertView;
        }

        class OnClickListenerImpl implements View.OnClickListener {

            private int gridChildPosition;

            OnClickListenerImpl(int gridChildPosition) {
                this.gridChildPosition = gridChildPosition;
            }

            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onGridItemClick(gridGroupPosition, gridChildPosition);
            }
        }
    }
}

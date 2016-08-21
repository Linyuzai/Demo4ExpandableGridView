package com.linyuzai.expandablegridview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/8/21 0021.
 */
public class XExpandableGridView extends ScrollView {

    private ExpandableGridView expandableGridView;

    public XExpandableGridView(Context context) {
        super(context);
        init(context);
    }

    public XExpandableGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XExpandableGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XExpandableGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        expandableGridView = new ExpandableGridView(context);
        expandableGridView.setVerticalScrollBarEnabled(false);
        expandableGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        setFillViewport(true);
        addView(expandableGridView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setExpandableGridAdapter(ExpandableGridAdapter adapter) {
        expandableGridView.isOverwriteOnMeasure = true;
        expandableGridView.setExpandableGridAdapter(adapter);
    }

    public ExpandableGridAdapter getExpandableGridAdapter() {
        return expandableGridView.getExpandableGridAdapter();
    }

    public boolean isGroupClickEnable() {
        return expandableGridView.isGroupClickEnable();
    }

    public void setGroupClickEnable(boolean groupClickEnable) {
        expandableGridView.setGroupClickEnable(groupClickEnable);
    }

    public void setOnGridItemClickListener(OnGridItemClickListener listener) {
        expandableGridView.setOnGridItemClickListener(listener);
    }

    public OnGridItemClickListener getOnGridItemClickListener() {
        return expandableGridView.getOnGridItemClickListener();
    }

    public void expandGroup(int groupPosition) {
        expandGroup(groupPosition, false);
    }

    public void expandGroup(int groupPosition, boolean animate) {
        expandableGridView.expandGroup(groupPosition, animate);
    }

    public void expandAll() {
        expandAll(false);
    }

    public void expandAll(boolean animate) {
        expandableGridView.expandAll(animate);
    }

    public void setGroupIndicator(Drawable indicator) {
        expandableGridView.setGroupIndicator(indicator);
    }

    public void setChildIndicator(Drawable indicator) {
        expandableGridView.setChildIndicator(indicator);
    }

    public ExpandableGridView getExpandableGridView() {
        return expandableGridView;
    }

    public void setExpandableGridView(ExpandableGridView expandableGridView) {
        this.expandableGridView = expandableGridView;
    }
}

package com.linyuzai.demo4expandablegridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.linyuzai.expandablegridview.ExpandableGridAdapter;
import com.linyuzai.expandablegridview.ExpandableGridView;
import com.linyuzai.expandablegridview.OnGridItemClickListener;
import com.linyuzai.expandablegridview.adapter.SimpleExpandableGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableGridView expandableGridView;
    SimpleExpandableGridAdapter<String> expandableGridAdapter;

    List<List<String>> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableGridView = (ExpandableGridView) findViewById(R.id.egv);
        strings = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            temp.add("" + i);
        for (int i = 0; i < 10; i++)
            strings.add(temp);
        expandableGridView.setExpandableGridAdapter(expandableGridAdapter = new MyAdapter(strings));
        //expandableGridView.setGroupClickable(false);
        expandableGridView.expandAll(true);
        expandableGridView.setOnGridItemClickListener(new OnGridItemClickListener() {
            @Override
            public void onGridItemClick(View view, int gridGroupPosition, int gridChildPosition) {
                String date = expandableGridAdapter.getData(gridGroupPosition, gridChildPosition);
                Toast.makeText(MainActivity.this, "p:" + gridGroupPosition + ",c:" + gridChildPosition, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAdapter extends SimpleExpandableGridAdapter<String> {

        public MyAdapter(List<List<String>> dataList) {
            super(dataList);
        }

        @Override
        public View getGridGroupView(int gridGroupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText("group:" + gridGroupPosition);
            return convertView;
        }

        @Override
        public View getGridChildView(int gridGroupPosition, int gridChildPosition, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText("child:" + getData(gridGroupPosition, gridChildPosition));
            return convertView;
        }

        @Override
        public int getNumColumns(int gridGroupPosition) {
            return gridGroupPosition % 3 + 3;
        }
    }

    class MyAdapter2 extends ExpandableGridAdapter {

        @Override
        public int getGridGroupCount() {
            return 0;
        }

        @Override
        public int getGridChildCount(int gridGroupPosition) {
            return 0;
        }

        @Override
        public View getGridGroupView(int gridGroupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public View getGridChildView(int gridGroupPosition, int gridChildPosition, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public int getNumColumns(int gridGroupPosition) {
            return 0;
        }
    }
}

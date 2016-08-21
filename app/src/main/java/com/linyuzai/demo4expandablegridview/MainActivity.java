package com.linyuzai.demo4expandablegridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.linyuzai.expandablegridview.ExpandableGridView;
import com.linyuzai.expandablegridview.OnGridItemClickListener;
import com.linyuzai.expandablegridview.XExpandableGridView;
import com.linyuzai.expandablegridview.adapter.SimpleExpandableGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    XExpandableGridView expandableGridView;

    List<List<String>> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableGridView = (XExpandableGridView) findViewById(R.id.xegv);
        strings = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            temp.add("" + i);
        for (int i = 0; i < 10; i++)
            strings.add(temp);
        expandableGridView.setExpandableGridAdapter(new MyAdapter(strings));
        expandableGridView.setGroupClickEnable(false);
        expandableGridView.expandAll();
        expandableGridView.setOnGridItemClickListener(new OnGridItemClickListener() {
            @Override
            public void onGridItemClick(int gridGroupPosition, int gridChildPosition) {
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
            textView.setText(getData(gridGroupPosition, gridGroupPosition));
            return convertView;
        }

        @Override
        public View getGridChildView(int gridGroupPosition, int gridChildPosition, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(getData(gridGroupPosition, gridChildPosition));
            return convertView;
        }

        @Override
        public int getNumColumns(int gridGroupPosition) {
            return gridGroupPosition % 5 + 1;
        }
    }
}

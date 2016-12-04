# Demo4ExpandableGridView
ExpandableGridView

![expandable_gridview.gif](http://upload-images.jianshu.io/upload_images/2113387-001ee73b6134ba9d.gif?imageMogr2/auto-orient/strip)
>忘记把鼠标录进来，点击事件和傻逼一样。。。
下面是分割线

```
<dependency> 
    <groupId>com.linyuzai</groupId>   
    <artifactId>expandablegridview</artifactId> 
    <version>1.2.0</version> 
    <type>pom</type>
</dependency>
------------------------------------------------
compile 'com.linyuzai:expandablegridview:1.2.0'
```

```
<com.linyuzai.expandablegridview.ExpandableGridView 
    xmlns:grid="http://schemas.android.com/apk/res-auto"    
    android:id="@+id/egv"    
    android:layout_width="match_parent"    
    android:layout_height="wrap_content"    
    grid:group_clickable="true"    
    grid:horizontal_spacing="10dp"    
    grid:overwrite_measure="true"    
    grid:vertical_spacing="10dp" />
```
>horizontal_spacing和vertical_spacing是GridView的属性，然后overwrite_measure，比如在外面套了一个ScrollView就不用自己重写了。如果group_clickable设为了false，发现ChildView不显示，点击也没用，调用一下ExpandableGridView.expandAll();接下来是Adapter~

```
public ExpandableGridAdapter getExpandableGridAdapter();

public void setExpandableGridAdapter(ExpandableGridAdapter adapter);

class MyAdapter1 extends ExpandableGridAdapter {    
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

class MyAdapter2 extends SimpleExpandableGridAdapter<String> {    
    public MyAdapter(List<List<String>> dataList) {        
        super(dataList);    
    }
    
    @Override    
    public View getGridGroupView(int gridGroupPosition, boolean isExpanded, View convertView, ViewGroup parent) {       
        return convertView;    
    }    

    @Override    
    public View getGridChildView(int gridGroupPosition, int gridChildPosition, View convertView, ViewGroup parent) {        
        return convertView;    
    }    

    @Override    
    public int getNumColumns(int gridGroupPosition) {        
        return 0;    
    }
}
```
>最后还有一个

```
public void setOnGridItemClickListener(OnGridItemClickListener listener);
```

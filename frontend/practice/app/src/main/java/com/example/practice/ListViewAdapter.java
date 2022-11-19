package com.example.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ImageView iconImageView;
    private TextView titleTextview;
    private TextView contentTextView;

    private ArrayList<ListViewItem> listviewItemList = new ArrayList<ListViewItem>();
    public ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listviewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
            layoutParams.height = 600;

            convertView.setLayoutParams(layoutParams);
        }

        titleTextview = (TextView) convertView.findViewById(R.id.title);
        iconImageView = (ImageView) convertView.findViewById(R.id.icon);
        contentTextView = (TextView) convertView.findViewById(R.id.text);

        ListViewItem listViewItem = listviewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextview.setText(listViewItem.getTitle());
        iconImageView.setImageResource(listViewItem.getIcon());
        contentTextView.setText(listViewItem.getContent());

        return convertView;
    }
    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return listviewItemList.get(position);
    }
    // 아이템 데이터 추가를 위한 함수.
    public void addItem(String title, int icon, String content) {
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setIcon(icon);
        item.setContent(content);

        listviewItemList.add(item);
    }

}
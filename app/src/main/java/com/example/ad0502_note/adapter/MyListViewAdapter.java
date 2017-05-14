package com.example.ad0502_note.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ad0502_note.dbutils.Note;
import com.example.ad0502_note.R;

import java.util.ArrayList;

/**
 * Created by yu on 2017/5/3.
 */

public class MyListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Note> list;

    public MyListViewAdapter(Context context, ArrayList<Note> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item, null);

            viewHolder.item_title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.item_type = (TextView) convertView.findViewById(R.id.item_type);
            viewHolder.item_date = (TextView) convertView.findViewById(R.id.item_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Note note = list.get(position);
        viewHolder.item_type.setText(note.getType());
        viewHolder.item_title.setText(note.getTitle());
        if(note.getModifyDate()!=null) {
            viewHolder.item_date.setText(note.getModifyDate());
        }else {
            viewHolder.item_date.setText(note.getCreateDate());
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView item_title;
        private TextView item_type;
        private TextView item_date;
    }
}

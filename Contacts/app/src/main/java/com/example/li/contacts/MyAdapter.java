package com.example.li.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Li on 2016/10/11 0011.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Person> persons;
    private Context context;
    private LinearLayout layout;
    public MyAdapter(ArrayList<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        layout = (LinearLayout) inflater.inflate(R.layout.cell, null);
//        TextView nameTv = (TextView) layout.findViewById(R.id.name);
//        TextView numberTv = (TextView) layout.findViewById(R.id.number);
//        nameTv.setText(persons.get(position).getName());
//        numberTv.setText(persons.get(position).getNumber());

        // 优化ListView
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cell, null);
            holder = new ViewHolder();
            holder.nameTv = (TextView) convertView.findViewById(R.id.name);
            holder.numberTv = (TextView) convertView.findViewById(R.id.number);
            holder.nameTv.setText(persons.get(position).getName());
            holder.numberTv.setText(persons.get(position).getNumber());
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private static class ViewHolder{
        TextView nameTv;
        TextView numberTv;
    }
}

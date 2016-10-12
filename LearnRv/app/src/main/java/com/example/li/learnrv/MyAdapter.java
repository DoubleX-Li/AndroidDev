package com.example.li.learnrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Li on 2016/9/22 0022.
 */
class MyAdapter extends RecyclerView.Adapter {

    class ViewHolder extends RecyclerView.ViewHolder {

        private View root;
        private TextView tvTitle, tvContent;

        public ViewHolder(View root) {
            super(root);

            tvTitle = (TextView) root.findViewById(R.id.tvTitle);
            tvContent = (TextView) root.findViewById(R.id.tvContent);
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvContent() {
            return tvContent;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, null));
    }

    // 可以对TextView进行赋值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        CellData cd = data[position];

        vh.getTvTitle().setText(cd.title);
        vh.getTvContent().setText(cd.content);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    private CellData[] data = new CellData[]{new CellData("极客学院", "IT职业教育"), new CellData("新闻", "这个新闻真不错"), new CellData("极客学院", "IT职业教育"), new CellData("新闻", "这个新闻真不错"), new CellData("极客学院", "IT职业教育"), new CellData("新闻", "这个新闻真不错"), new CellData("极客学院", "IT职业教育"), new CellData("新闻", "这个新闻真不错")};
}

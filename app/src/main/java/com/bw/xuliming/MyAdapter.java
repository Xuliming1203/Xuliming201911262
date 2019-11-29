package com.bw.xuliming;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * 时间：2019/11/26
 * 作者：徐黎明
 * 类的作用：适配器优化
 */
class MyAdapter extends BaseAdapter {
    private List<JavaBean.ListdataBean> list;

    public MyAdapter(List<JavaBean.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = list.get(position).getItemType();
        if (itemType == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        int itemViewType = getItemViewType(i);
        if (view == null) {
            if (itemViewType == 0) {
                viewHolder = new ViewHolder();
                view = View.inflate(viewGroup.getContext(), R.layout.item, null);
                viewHolder.iv = view.findViewById(R.id.iv);
                viewHolder.tv1 = view.findViewById(R.id.tv1);
                viewHolder.tv2 = view.findViewById(R.id.tv2);
                view.setTag(viewHolder);
            } else {
                viewHolder = new ViewHolder();
                view = View.inflate(viewGroup.getContext(), R.layout.item1, null);
                viewHolder.iv = view.findViewById(R.id.iv);
                viewHolder.tv1 = view.findViewById(R.id.tv1);
                viewHolder.tv2 = view.findViewById(R.id.tv2);
                view.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        JavaBean.ListdataBean listdataBean = list.get(i);
        viewHolder.tv1.setText(listdataBean.getContent());
        viewHolder.tv2.setText(listdataBean.getTitle());
        String imageurl = listdataBean.getImageurl();
        NetUtils.getInstance().getpricute(imageurl, viewHolder.iv);
        return view;
    }

    class ViewHolder {
        ImageView iv;
        TextView tv1;
        TextView tv2;
    }
}

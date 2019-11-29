package com.bw.xuliming;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.base.BaseFragment;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 时间：2019/11/26
 * 作者：徐黎明
 * 类的作用：主页
 */
public class HomeFragment extends BaseFragment {
    int page = 1;
    private XListView lv;
    List<JavaBean.ListdataBean> list = new ArrayList<>();
    private ImageView iv8;

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        lv = view.findViewById(R.id.lv);
        iv8 = view.findViewById(R.id.iv8);
        lv.setPullRefreshEnable(true);
        lv.setPullLoadEnable(true);
        final String url = "https://www.thepaper.cn/newsDetail_forward_4923534";
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        lv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                getData();
                lv.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData();
                lv.stopLoadMore();
            }
        });
    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        if (NetUtils.getInstance().hasNet(getContext())) {
            iv8.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            String httpUrl = "";
            if (page == 1) {
                httpUrl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
            } else {
                httpUrl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
            }
            NetUtils.getInstance().getjson(httpUrl, new NetUtils.MyCallBack() {
                @Override
                public void ongetjson(String json) {
                    Log.e("TAG", json);
                    JavaBean javaBean = new Gson().fromJson(json, JavaBean.class);
                    List<JavaBean.ListdataBean> listdata = javaBean.getListdata();
                    list.addAll(listdata);
                    lv.setAdapter(new MyAdapter(list));
                }
            });
        } else {
            iv8.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
        }
    }

}

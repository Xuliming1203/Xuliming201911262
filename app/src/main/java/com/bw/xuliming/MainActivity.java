package com.bw.xuliming;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bw.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 时间：2019/11/26
 * 作者：徐黎明
 * 类的作用：tab加viewpager
 */
public class MainActivity extends BaseActivity {
    List<Fragment> fragmentList = new ArrayList<>();
    private TabLayout tab;
    private ViewPager vp;
    List<String> strings = new ArrayList<>();
    private ImageView ivv;

    @Override
    protected void initView() {
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
        ivv = findViewById(R.id.ivv);
        ivv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });
        HomeFragment homeFragment = new HomeFragment();
        HomeFragment homeFragment1 = new HomeFragment();
        HomeFragment homeFragment2 = new HomeFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(homeFragment1);
        fragmentList.add(homeFragment2);
        strings.add("推荐");
        strings.add("要闻");
        strings.add("视频");
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings.get(position);
            }
        });
        tab.setupWithViewPager(vp);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
            ivv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

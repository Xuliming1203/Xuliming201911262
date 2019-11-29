package com.bw.xuliming;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bw.base.BaseActivity;

/**
 * 时间：2019/11/26
 * 作者：徐黎明
 * 类的作用：网页
 */
public class SecondActivity extends BaseActivity {

    private WebView web;
TextView textView;
    @Override
    protected void initView() {
        web = findViewById(R.id.web);
        final String url = getIntent().getStringExtra("url");
        web.loadUrl(url);
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.e("x", "在本页面加载");
                view.loadUrl(url);
                return true;
                //return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("x", "开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("x", "记载完成");
            }
        });
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("x", "当前进度为" + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("x", "标题加载完成");
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.e("x", "图片加载完成");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }
}

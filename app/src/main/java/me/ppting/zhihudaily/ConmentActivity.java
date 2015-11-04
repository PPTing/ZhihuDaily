package me.ppting.zhihudaily;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PPTing on 15/11/4.
 */
public class ConmentActivity extends ActionBarActivity {
    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout mCollapsingtoolbarlayout;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    //定义全局变量
    private String mTitle;
    private String mUrl;
    private String mImageUrl;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.contentview);
        ButterKnife.bind(this);

        getData();
        setupToolbar();
        setImage();
        setWebView();
    }

    private void setWebView() {
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //mWebView.loadUrl("http://news-at.zhihu.com/api/4/news/3892357");
        Log.d("ConmentActivity","murl is "+mUrl);
        mWebView.loadUrl(mUrl);
    }

    private void getData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        Log.d("", "mtitle is " + mTitle);
        mUrl = intent.getStringExtra("content");
        mImageUrl = intent.getStringExtra("image");
        Log.d("ConmentActivity", "murl is " + mUrl);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingtoolbarlayout.setTitle(mTitle);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //异步加载题图
    private void setImage()
    {
        PictureLoader pictureLoader = new PictureLoader();
        String mUrl = mImageUrl;
        mImage.setTag(mUrl);
        pictureLoader.showImageByAsyncTask(mImage,mUrl);
    }
}

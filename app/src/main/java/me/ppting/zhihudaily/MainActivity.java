package me.ppting.zhihudaily;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends ActionBarActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.navigationview)
    NavigationView mNavigationView;
    @Bind(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        if (mNavigationView == null) {setupDrawerContent();}
        getZhihuInfo();
    }
    //获取知乎返回的json
    private void getZhihuInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                mUrl = "http://news-at.zhihu.com/api/1.2/news/latest";
                HttpGet mHttpGet = new HttpGet(mUrl);
                try {
                    HttpResponse httpResponse = httpClient.execute(mHttpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200)
                    {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "UTF-8");
                        Log.d("MainActivity","response is "+response);
                        AnalyzeJson mAnalyzeJson = new AnalyzeJson();
                        mAnalyzeJson.AnalyzeData(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void setupDrawerContent() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Closing drawer on item click
                mDrawerLayout.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action
//                switch (menuItem.getItemId()) {
//                    case R.id.movie:
//                        return startIntent(MovieActivity.class);
//                    case R.id.music:
//                        return startIntent(MusicActivity.class);
//                    case R.id.life:
//                        return startIntent(LifeActivity.class);
//                    case R.id.aboutme:
//                        return startIntent(AboutMeActivity.class);
//                    default:
                return true;
//                }
            }
        });
    }

    private void setupToolbar() {
        toolbar.setTitle("知乎日报");
        toolbar.setTitleTextColor(Color.parseColor("#FFEB3B"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键监听
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name)
        {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }
    private boolean startIntent(Class mClass) {
        startActivity(new Intent(MainActivity.this, mClass));
        return true;
    }


}

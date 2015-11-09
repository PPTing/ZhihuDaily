package me.ppting.zhihudaily;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PPTing on 15/10/26.
 */
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG = PageFragment.class.getName();
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private int mPage;
    private String mDate;
    private String mUrl;


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        getDate();
        getZhihuInfo();
        ButterKnife.bind(this, view);
        setFabShowOrHide();
        return view;
    }

    private void setFabShowOrHide() {
        mRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void getDate() {
        Time mTime = new Time();
        mTime.setToNow();
        int mYear = mTime.year;
        int mMonth = mTime.month + 1;
        int mDay = mTime.monthDay + 1;
        Log.d("MainActivity", "mMonth is " + mMonth);
        if (mTime.monthDay < 10) {
            mDate = mYear + "" + mMonth + "0" + mDay;
        } else {
            mDate = mYear + "" + mMonth + mDay;
        }
        Log.d("MainActivity", "today's date is " + mDate);
    }

    //获取知乎返回的json
    private void getZhihuInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                Log.d("getZhiHuInfo", "mDate is " + mDate);
                int mDateId = Integer.parseInt(mDate) - mPage;

                Log.d(TAG,"mDateId is "+mDateId);
                mUrl = String.format("http://news.at.zhihu.com/api/1.2/news/before/%s", mDateId);
                Log.d(TAG,"mPage is "+mPage);
                Log.d(TAG,"mUrl is "+mUrl);
                HttpGet mHttpGet = new HttpGet(mUrl);
                try {
                    HttpResponse httpResponse = httpClient.execute(mHttpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "UTF-8");
                        Log.d(TAG, "response is " + response);
                        //AnalyzeJson mAnalyzeJson = new AnalyzeJson();
                        //mAnalyzeJson.AnalyzeData(response);
                        //异步加载标题和缩略图
                        MyAsyncTask myAsyncTask = new MyAsyncTask();
                        myAsyncTask.execute(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MyAsyncTask extends AsyncTask<String, Void, List<ZhihuBean>> {
        @Override
        protected List<ZhihuBean> doInBackground(String... params) {
            Log.d(TAG, "传入的JsonData是 " + params[0]);
            List<ZhihuBean> response = new AnalyzeJson().AnalyzeData(params[0]);
            return response;
        }

        @Override
        protected void onPostExecute(final List<ZhihuBean> zhihuBeans) {
            super.onPostExecute(zhihuBeans);
            Log.d(TAG, "zhihuBeans is " + zhihuBeans);
            //dLog.d(TAG,"mPage is "+mPage);
            ZhihuAdapter zhihuAdapter = new ZhihuAdapter(getActivity(), zhihuBeans);
            mRecyclerview.setAdapter(zhihuAdapter);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerview.setLayoutManager(mLayoutManager);
            zhihuAdapter.setOnClickListener(new ZhihuAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    Log.d(TAG, "Click item");
                    Intent intent = new Intent(getActivity(), ConmentActivity.class);
                    intent.putExtra("title", zhihuBeans.get(position).title);
                    intent.putExtra("image", zhihuBeans.get(position).imageUrl);
                    intent.putExtra("content", zhihuBeans.get(position).shareUrl);
                    startActivity(intent);
                }
            });
        }


    }
}

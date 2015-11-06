package me.ppting.zhihudaily;

import android.text.Html;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Created by PPTing on 15/11/3.
 */
public class AnalyzeJson {
    private static String TAG = AnalyzeJson.class.getName();
    public List<ZhihuBean> AnalyzeData(String jsonData)
    {
        List<ZhihuBean> mZhihuInfoList = new ArrayList<>();
        Gson gson = new Gson();
        ZhihuInfo mZhihuInfo = gson.fromJson(jsonData,ZhihuInfo.class);
        try {
            Log.d(TAG, "news's size is " + mZhihuInfo.getNews().size());
            for (int i = 1; i < mZhihuInfo.getNews().size(); i++) {
                ZhihuBean mZhihuBean = new ZhihuBean();
                Log.d(TAG,"i is "+i);
                mZhihuBean.title = mZhihuInfo.getNews().get(i).getTitle();
                mZhihuBean.thumbnailUrl = mZhihuInfo.getNews().get(i).getThumbnail();
                mZhihuBean.imageUrl = mZhihuInfo.getNews().get(i).getImage();
                mZhihuBean.shareUrl = mZhihuInfo.getNews().get(i).getShare_url();
                Log.d("AnalyzeJson", "title is " + mZhihuInfo.getNews().get(i).getTitle());
                mZhihuInfoList.add(mZhihuBean);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return mZhihuInfoList;
    }


}

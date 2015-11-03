package me.ppting.zhihudaily;

/**
 * Created by PPTing on 15/10/26.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureLoader
{
    private LruCache<String,Bitmap> mCache ;

    public PictureLoader()
    {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/4;
        mCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void showImageByAsyncTask(ImageView imageView,String url)
    {
        //MyAsyncTask myAsyncTask = new MyAsyncTask(imageView,url);
        //myAsyncTask.execute(url);
        Bitmap bitmap = getBitmapFromCache(url);
        //判断缓存中的bitmap是否为空，如果空进行异步下载，否则使用缓存中的bitmap
        if (bitmap == null) {
            if (imageView.getTag().equals(url)) {
                new ShowPicAsyncTask(imageView, url).execute(url);
            }
        }else {
            if (imageView.getTag().equals(url)) {
                imageView.setImageBitmap(bitmap);
            }
        }

    }
    //通过url得到bitmap
    public Bitmap getBitmapFromUrl(String stringUrl)
    {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        return null;
    }
    //异步加载天气列表里的天气图
    private class ShowPicAsyncTask extends AsyncTask<String,Void,Bitmap>
    {
        private ImageView mImageView;
        private String mUrl;

        public ShowPicAsyncTask(ImageView imageView,String url)
        {
            mImageView = imageView;
            mUrl = url;

        }
        @Override
        protected Bitmap doInBackground(String... params) {
            //从URL中下载图片并保存到内存中
            Bitmap bitmap = getBitmapFromUrl(params[0]);
            if(bitmap != null){
                addBitmapToCache(params[0],bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap(bitmap);
                //mImageView.setBackgroundResource(R.drawable.drawer_header);
            }
        }

    }

    public Bitmap getBitmapFromCache(String url) {
        return mCache.get(url);
    }
    public void addBitmapToCache(String url,Bitmap bitmap)
    {
        if(getBitmapFromCache(url) == null)
        {
            mCache.put(url, bitmap);
        }
    }

}

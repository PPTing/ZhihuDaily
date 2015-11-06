package me.ppting.zhihudaily;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by PPTing on 15/11/3.
 */
public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.MyViewHolder> {

    private Context mContext;
    private List<ZhihuBean> mDataList;
    private LayoutInflater mLayoutInflater;
    public ZhihuAdapter(Context context,List<ZhihuBean> data){
        this.mContext = context;
        this.mDataList = data;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public interface OnItemClickListener
    {
        void OnItemClick(View view,int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.onItemClickListener = mOnItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.cardview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.d("ZhihuAdapter","title is "+mDataList.get(position).title);
        holder.mTextView.setText(mDataList.get(position).title);

        PictureLoader pictureLoader = new PictureLoader();
        String mUrl = mDataList.get(position).thumbnailUrl;
        Log.d("ZhihuAdapter","mUrl is "+mUrl);

        holder.mImageView.setTag(mUrl);
        pictureLoader.showImageByAsyncTask(holder.mImageView,mUrl);

        if (onItemClickListener!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.OnItemClick(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mTextView;
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.newsimage);
            mTextView = (TextView) itemView.findViewById(R.id.newstitle);
        }
    }
}

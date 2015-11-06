package me.ppting.zhihudaily;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by PPTing on 15/11/6.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<String> mTitles;
    public PagerAdapter(FragmentManager fm,List<String> titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }
}

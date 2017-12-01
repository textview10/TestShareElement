package com.zonekey.testshareelement.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zonekey.testshareelement.MainActivity;
import com.zonekey.testshareelement.PhotoViewActivity;

import java.util.ArrayList;

/**
 * Created by xu.wang
 * Date on  2017/11/30 19:17:22.
 *
 * @Desc
 */

public class PhotoViewAdapter extends PagerAdapter {
    private ArrayList<String> mLists;
    private PhotoViewActivity mActivity;
    private PhotoViewActivity.OnCreateTargetViewListener mListener;

    public PhotoViewAdapter(PhotoViewActivity activity, ArrayList<String> lists, PhotoViewActivity.OnCreateTargetViewListener listener) {
        this.mLists = lists;
        this.mActivity = activity;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mLists == null ? 0 : mLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setTag(position);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Glide.with(container.getContext()).load(mLists.get(position)).into(imageView);
        if (position == mActivity.pos){
            mListener.targetView(imageView);
        }
        container.addView(imageView, layoutParams);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container);
    }
}

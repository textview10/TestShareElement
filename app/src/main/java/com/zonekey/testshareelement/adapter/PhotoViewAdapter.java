package com.zonekey.testshareelement.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zonekey.testshareelement.MainActivity;
import com.zonekey.testshareelement.PhotoViewActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

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
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView photoView = new ImageView(container.getContext());
        photoView.setTag(position);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Glide.with(container.getContext()).load(mLists.get(position)).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (position == mActivity.pos) {
                    mListener.targetView(photoView);
                }
                return false;
            }
        }).into(photoView);

        container.addView(photoView, layoutParams);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container);
    }
}

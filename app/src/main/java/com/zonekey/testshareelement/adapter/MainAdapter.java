package com.zonekey.testshareelement.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zonekey.testshareelement.MainActivity;
import com.zonekey.testshareelement.PhotoViewActivity;
import com.zonekey.testshareelement.R;
import com.zonekey.testshareelement.holder.MainHolder;

import java.util.ArrayList;

/**
 * Created by xu.wang
 * Date on  2017/11/30 15:34:12.
 *
 * @Desc
 */

public class MainAdapter extends RecyclerView.Adapter<MainHolder> {
    private ArrayList<String> mLists;
    private MainActivity mActivity;

    public MainAdapter(MainActivity activity, ArrayList<String> lists) {
        this.mActivity = activity;
        this.mLists = lists;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainHolder holder, final int position) {
        Glide.with(holder.iv_main.getContext()).load(mLists.get(position)).centerCrop().into(holder.iv_main);
        holder.iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, PhotoViewActivity.class);
                intent.putExtra(PhotoViewActivity.TAG_PHOTO, position);
                intent.putStringArrayListExtra(PhotoViewActivity.TAG_LIST, mLists);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mActivity.pos = position;
                    Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, holder.iv_main, mActivity.SHARE_IMAGE_NAME).toBundle();
                    ActivityCompat.startActivityForResult(mActivity, intent, 111, options);
                } else {
                    mActivity.startActivityForResult(intent, 111);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }
}

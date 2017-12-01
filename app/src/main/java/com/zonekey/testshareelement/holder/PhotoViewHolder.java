package com.zonekey.testshareelement.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.zonekey.testshareelement.R;
import com.zonekey.testshareelement.view.CheckImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xu.wang
 * Date on 2016/11/1 15:18
 */
public class PhotoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.checkbox_photo)
    public CheckImageView mCheckBox;
    @BindView(R.id.item_photo)
    public ImageView mImageView;
    @BindView(R.id.item_content)
    public RelativeLayout rl_main;
    public PhotoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

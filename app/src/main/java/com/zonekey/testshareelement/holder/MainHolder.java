package com.zonekey.testshareelement.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zonekey.testshareelement.R;

/**
 * Created by xu.wang
 * Date on  2017/11/30 15:35:32.
 *
 * @Desc
 */

public class MainHolder extends RecyclerView.ViewHolder {
    public ImageView iv_main;

    public MainHolder(View itemView) {
        super(itemView);
        iv_main = itemView.findViewById(R.id.iv_main_item);
    }
}

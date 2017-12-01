package com.zonekey.testshareelement.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xu.wang
 * Date on 2016/11/1 17:01
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int grid_num;
    private boolean normal = false; //第一个元素和最后一个元素是否设置左侧距离和右侧距离
    public SpaceItemDecoration(int space,int grid_num) {
        this.space = space;
        this.grid_num = grid_num;
    }

    /**
     * normla为true说明左侧和右侧有距离
     * @param space
     * @param grid_num
     * @param normal
     */
    public SpaceItemDecoration(int space,int grid_num,boolean normal) {
        this.space = space;
        this.grid_num = grid_num;
        this.normal = normal;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (!normal) {
            if (parent.getChildLayoutPosition(view) % grid_num == 0) {
                outRect.left = 0;
            }
        }
    }
}

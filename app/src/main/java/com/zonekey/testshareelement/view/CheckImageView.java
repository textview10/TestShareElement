package com.zonekey.testshareelement.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.zonekey.testshareelement.R;


/**
 * Created by xu.wang
 * Date on 2016/11/3 17:41
 */
public class CheckImageView extends AppCompatImageView implements View.OnClickListener {
    private boolean isChecked = false;
    private OnCheckImageView mListener;

    @Override
    public void onClick(View view) {
        isChecked = !isChecked;
        if (mListener != null) {
            mListener.checkChange(isChecked);
        }
        if (isChecked) {
            setImageResource(R.drawable.checkbox_press);
        } else {
            setImageResource(R.drawable.checkbox_normal);
        }
    }

    public interface OnCheckImageView {
        void checkChange(boolean isChecked);
    }

    /**
     * 选中状态变化后
     * @param mListener
     */
    public void setOnCheckImageViewListener(OnCheckImageView mListener) {
        this.mListener = mListener;
    }

    public CheckImageView(Context context) {
        super(context);
    }

    public CheckImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(this);
    }

    public CheckImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        if (isChecked) {
            setImageResource(R.drawable.checkbox_press);
        } else {
            setImageResource(R.drawable.checkbox_normal);
        }
    }
}

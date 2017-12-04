package com.zonekey.testshareelement;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.zonekey.testshareelement.adapter.PhotoViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xu.wang
 * Date on  2017/11/30 15:51:05.
 *
 * @Desc
 */

public class PhotoViewActivity extends AppCompatActivity {
    private final String TAG = "PhotoViewActivity";
    private ViewPager vp_photo;
    public static final String TAG_PHOTO = "tag_photo";
    public static final String TAG_LIST = "tag_list";
    public static final String TAG_POS = "tag_pos";
    public int pos;
    private ArrayList<String> mLists;
    private TextView tv_pos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_photo_view);
        pos = getIntent().getIntExtra(TAG_PHOTO, -1);
        mLists = getIntent().getStringArrayListExtra(TAG_LIST);
        initialView();
    }

    private void initialView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    super.onMapSharedElements(names, sharedElements);
                    ImageView imageView = getCurrentView();
                    if (imageView != null) {
                        sharedElements.put(MainActivity.SHARE_IMAGE_NAME, imageView);
                    }
                }
            });
        }

        vp_photo = findViewById(R.id.vp_photo_view);
        ViewCompat.setTransitionName(vp_photo, MainActivity.SHARE_IMAGE_NAME);
        tv_pos = findViewById(R.id.tv_pos);
        if (pos != -1) {
            tv_pos.setText("" + pos);
        }
        PhotoViewAdapter photoViewAdapter = new PhotoViewAdapter(this, mLists, new OnCreateTargetViewListener() {
            @Override
            public void targetView(View view) {
//                ViewCompat.setTransitionName(view, MainActivity.SHARE_IMAGE_NAME);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startPostponedEnterTransition();
                }
            }
        });
        vp_photo.setAdapter(photoViewAdapter);

        vp_photo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                tv_pos.setText("" + pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (pos != -1) {
            vp_photo.setCurrentItem(pos);
        }
    }

    private ImageView getCurrentView() {
        return vp_photo.findViewWithTag(pos);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(TAG_POS, pos);
        setResult(111, intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    public interface OnCreateTargetViewListener {
        void targetView(View view);
    }
}
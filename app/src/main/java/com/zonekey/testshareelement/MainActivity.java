package com.zonekey.testshareelement;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.zonekey.testshareelement.adapter.MainAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String SHARE_IMAGE_NAME = "share_image_name";
    private RecyclerView rv_main;
    private MainAdapter mainAdapter;
    public int pos = 0;
    private ArrayList<String> mTotalImages = new ArrayList<>();
    private final int SCAN_ALL_OK = 111;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_ALL_OK:
                    mainAdapter.notifyDataSetChanged();
                    break;
            }

        }

    };
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialData();
    }

    private void initialView() {
        rv_main = findViewById(R.id.rv_main);
        layoutManager = new GridLayoutManager(this, 3);
        rv_main.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(this, mTotalImages);
        rv_main.setAdapter(mainAdapter);
    }


    private void initialData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setExitSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    super.onMapSharedElements(names, sharedElements);
                    View view = findViewByPostion();
                    if (view != null) {
                        sharedElements.put(SHARE_IMAGE_NAME, view);
                    }
                    Log.e("Callback", "pos =" + pos + "view == null ? " + (view == null));
                }
            });
        }
        getALLImages();
    }


    private View findViewByPostion() {
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (pos - firstItemPosition >= 0) {
            //得到要更新的item的view
            View view = rv_main.getChildAt(pos - firstItemPosition);
            return view;
        }
        return null;
    }

    /**
     * 利用ContentProvider扫描手机中所有的图片，此方法在运行在子线程中
     */
    private void getALLImages() {
        mTotalImages.clear();
        new Thread() {
            @Override
            public void run() {
                super.run();
                Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media.DATA}
                        , null, null, MediaStore.Images.Media.DATE_MODIFIED + " DESC");
                while (cursor.moveToNext()) {       //获取图片的路径
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    File file = new File(path);
                    if (file.exists() && file.length() > 0) {
                        mTotalImages.add(path);
                    }
                }
                cursor.close();
                mHandler.sendEmptyMessage(SCAN_ALL_OK);
            }
        }.start();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == 111) {
            final int pos = data.getIntExtra(PhotoViewActivity.TAG_POS, -1);
            if (pos != -1) {
                this.pos = pos;
                Log.e("onActivityReenter", "activity result =" + pos );
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    postponeEnterTransition();
                    final View view = findViewByPostion();
                    if (view == null) return;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startPostponedEnterTransition();
                    }
                }
            }
        }
    }

}

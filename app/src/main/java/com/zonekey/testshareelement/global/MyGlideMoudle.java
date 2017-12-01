package com.zonekey.testshareelement.global;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.zonekey.testshareelement.R;

/**
 * Created by xu.wang
 * Date on 2017/3/3 10:30
 * glide初始化的全局配置
 */
public class MyGlideMoudle implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
        //设置50M硬盘缓存
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide_cache", 50 * 1024 * 1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}

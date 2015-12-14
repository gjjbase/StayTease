package gjj.staytease.com.util;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * glide setting
 * Created by jayuchou on 15/10/22.
 */
public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 配置图片将缓存到SD卡
        ExternalCacheDiskCacheFactory externalCacheDiskCacheFactory = new ExternalCacheDiskCacheFactory(context);
        builder.setDiskCache(externalCacheDiskCacheFactory);

        // 如果配置图片将缓存到SD卡后那么getPhotoCacheDir返回仍然没有变化
        Log.w("jayuchou", Glide.getPhotoCacheDir(context).getPath());
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // 配置使用OKHttp来请求网络
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}

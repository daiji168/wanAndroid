package cn.wanandroid.dj.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.util.Util;

import java.io.File;
import java.util.concurrent.ExecutionException;


/**
 * Created by Administrator on 2016/8/25.
 */
public class GlideLoadImageUtils {

    /**
     * 加载圆形图片
     */
    public static void loadCircleImage(Context context, String path, ImageView imageView, int placeholder, int error) {
        if (Util.isOnMainThread()) {
            Glide.with(context).load(path)
                    .transform(new GlideCircleTransform(context))
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        }
    }

    public static void loadCircleImage(Context context, String path, ImageView imageView, int placeholder) {
        try {
            if (Util.isOnMainThread()) {
                Glide.with(context).load(path)
                        .transform(new GlideCircleTransform(context))
                        .placeholder(placeholder)
                        .into(imageView);
            }
        } catch (Exception e) {
        }

    }

    public static void loadCircleImage(Context context, String path, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context).load(path)
                    .transform(new GlideCircleTransform(context))
                    .into(imageView);
        }
    }

    /**
     * 加载正常图片
     */
    public static void loadNormalImage(Context context, String path, ImageView imageView, int placeholder, int error) {
        if (Util.isOnMainThread()) {
            Glide.with(context).load(path)
                    .centerCrop()
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        }
    }

    public static void loadNormalImage(Context context, String path, ImageView imageView, int placeholder) {
        try {
            if (Util.isOnMainThread()) {
                Glide.with(context).load(path)
                        .centerCrop()
                        .placeholder(placeholder)
                        .into(imageView);
            }
        } catch (Exception e) {
        }
    }

    /**
     * @param context
     * @param path
     * @param imageView
     * @param placeholder
     * 不对图片进行缓存
     */
    public static void loadNoCacheImage(Context context, String path, ImageView imageView, int placeholder) {
        try {
            if (Util.isOnMainThread()) {
                Glide.with(context).load(path)
                        .centerCrop()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(placeholder)
                        .into(imageView);
            }
        } catch (Exception e) {
        }
    }

    private static final String TAG = GlideLoadImageUtils.class.getSimpleName();

    public static void loadNormalImage(Context context, String path, ImageView imageView) {
        try {
            if (Util.isOnMainThread()) {
                Glide.with(context).load(path)
                        .centerCrop()
                        .crossFade()
                        .into(imageView);
            }
        } catch (Exception e) {
        }
    }

    public static String getImgPathFromCache(Context context, String url, int width, int height) {
        FutureTarget<File> fileFutureTarget = Glide.with(context).load(url).downloadOnly(width, height);
        File cacheFile = null;
        try {
            cacheFile = fileFutureTarget.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String absolutePath = cacheFile.getAbsolutePath();
        return absolutePath;
    }

}

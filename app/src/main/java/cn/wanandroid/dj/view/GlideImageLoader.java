package cn.wanandroid.dj.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import cn.wanandroid.dj.bean.BannerModle;
import cn.wanandroid.dj.utils.GlideLoadImageUtils;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        if(path instanceof BannerModle){
            GlideLoadImageUtils.loadNormalImage(context.getApplicationContext(),((BannerModle)path).getImagePath(),imageView);
        }else{
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .crossFade()
                    .into(imageView);
        }
    }
}

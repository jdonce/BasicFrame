package com.donce.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 加载图片的帮助类
 * Created by Administrator on 2016/7/27 0027.
 */
public class GlideHelper {

    public static final int CIRCLE_ICON = 0;//圆形图
    public static final int ROUNDED_CORNERS_ICON = 1;//圆角图
    public static final int GRAY_ICON = 2;//灰色图
    public static final int BLUR_ICON = 3;//高斯模糊图

    private static RequestManager getWith(Context context) {
        return Glide.with(context);
    }

    //显示网络图片
    public static void showNetworkIcon(Context context, String url, ImageView imageView) {
        getWith(context)
                .load(url)
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .into(imageView);
    }

    //显示网络图片
    public static void showNetworkIcon(Context context, String url, ImageView imageView,
                                       int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(url)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .into(imageView);
    }

    //从Uri中加载
    public static void showUrlIcon(Context context, Uri url, ImageView imageView) {
        getWith(context)
                .load(url)
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .into(imageView);
    }

    //从Uri中加载
    public static void showUrlIcon(Context context, Uri url, ImageView imageView,
                                   int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(url)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .into(imageView);
    }

    //加载资源图片
    public static void showResourceIcon(Context context, int resourceId, ImageView imageView) {
        getWith(context).load(resourceId).into(imageView);
    }

    //显示缩略图
    public static void showNetworkThumbnailIcon(Context context, String url, ImageView imageView, float size,
                                                int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(url)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .thumbnail(size)
                .into(imageView);

    }

    //显示缩略图
    public static void showUrlThumbnailIcon(Context context, Uri url, ImageView imageView, float size,
                                            int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(url)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .thumbnail(size)
                .into(imageView);
    }

    //显示缩略图
    public static void showResourceThumbnailIcon(Context context, int resourceId, ImageView imageView,
                                                 float size, int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(resourceId)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .thumbnail(size)
                .into(imageView);
    }

    //显示特殊图片 根据iconType显示(圆形、圆角、灰色、高斯模糊)
    public static void showNetworkTransformationIcon(Context context, String url, ImageView imageView,
                                                     int iconType, int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(url)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .bitmapTransform(getBitmapTransformation(context, iconType))
                .into(imageView);
    }

    //显示特殊图片 根据iconType显示(圆形、圆角、灰色、高斯模糊)
    public static void showUrlTransformationIcon(Context context, Uri url, ImageView imageView,
                                                 int iconType, int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(url)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .bitmapTransform(getBitmapTransformation(context, iconType))
                .into(imageView);
    }

    //显示特殊图片 根据iconType显示(圆形、圆角、灰色、高斯模糊)
    public static void showResourceTransformationIcon(Context context, int resourceId, ImageView imageView,
                                                      int iconType, int loadResourceId, int errorResourceId) {
        getWith(context)
                .load(resourceId)
                .placeholder(loadResourceId)//加载中显示的图片
                .error(errorResourceId)//加载失败时显示的图片
                .crossFade()//淡入显示(也可设置时间),注意:如果设置了这个,则必须要去掉asBitmap
                .bitmapTransform(getBitmapTransformation(context, iconType))
                .into(imageView);
    }

    @NonNull
    private static Transformation<Bitmap> getBitmapTransformation(Context context, int iconType) {
        Transformation<Bitmap> transformation = null;
        switch (iconType) {
            case CIRCLE_ICON://圆形
                transformation = new CropCircleTransformation(context);
                break;
            case ROUNDED_CORNERS_ICON://圆角
                transformation = new RoundedCornersTransformation(context, 5, 5);
                break;
            case GRAY_ICON://灰色
                transformation = new GrayscaleTransformation(context);
                break;
            case BLUR_ICON://高斯模糊
            default:
                transformation = new BlurTransformation(context);
                break;
        }
        return transformation;
    }
}

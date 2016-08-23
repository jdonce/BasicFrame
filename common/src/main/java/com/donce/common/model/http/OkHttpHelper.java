package com.donce.common.model.http;

import com.donce.common.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * okhttp的帮助类
 * Created by Administrator on 2016/7/20 0020.
 */
public class OkHttpHelper {
    //OkHttpClient的创建
    public static OkHttpClient create() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();//网络请求日志拦截器
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(BaseApplication.getInstance().getCacheDir(), "android-test");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();
        return okHttpClient;
    }
}

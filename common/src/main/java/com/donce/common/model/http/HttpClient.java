package com.donce.common.model.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class HttpClient {
    private static HttpClient mInstance;
    private Retrofit singleton;


    public static HttpClient getIns(String base_url) {
        if (mInstance == null) {
            synchronized (HttpClient.class) {
                if (mInstance == null) mInstance = new HttpClient(base_url);
            }
        }
        return mInstance;
    }

    public HttpClient(String base_url) {
        singleton = new Retrofit.Builder()
                .baseUrl(base_url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//增加 RxJava 适配器 使用RxJava作为回调适配器
                .addConverterFactory(GsonConverterFactory.create())//增加 Gson 转换器
                .client(OkHttpHelper.create())
                .build();
    }

    public <T> T createService(Class<T> clz) {
        return singleton.create(clz);
    }



}

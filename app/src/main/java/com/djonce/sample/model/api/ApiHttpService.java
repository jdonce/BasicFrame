package com.djonce.sample.model.api;

import com.djonce.sample.model.bean.BaseResponse;
import com.djonce.sample.model.bean.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 用于管理接口的调用
 * Created by Administrator on 2016/7/20 0020.
 */
public interface ApiHttpService {
    @GET("api/user.json")
    Call<BaseResponse<User>> getUser(@Query("cellphone") String cellphone, @Query("password") String password);

    @GET("api/user.json")
    Observable<BaseResponse<User>> getUser1(@Query("cellphone") String cellphone, @Query("password") String password);

    @Multipart
    @POST("users")
    Call<BaseResponse<String>> upload(@PartMap Map<String, RequestBody> params, @Part("file") List<MultipartBody.Part> parts);

    @Multipart
    @POST("users")
    Call<BaseResponse<String>> upload(@Part("file") List<MultipartBody.Part> parts);


    @POST("users")
    Call<BaseResponse<String>> upload(@Body MultipartBody multipartBody);

    @GET
    Call<ResponseBody> download(@Url String fileUrl);

    @GET("api/user.json")
    Call<BaseResponse<User>> getUser(@QueryMap Map<String, String> params);

}

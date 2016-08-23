package com.djonce.sample.presenter;


import com.djonce.sample.model.api.Factory;
import com.djonce.sample.model.bean.BaseResponse;
import com.donce.common.util.FilesToMultipartHelper;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class FileUploadPresenter {

    public void upload(String[] filePaths) {
        List<MultipartBody.Part> parts = FilesToMultipartHelper.filesToMultipart("description", filePaths);
        Factory.provideHttpService().upload(parts).enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {

            }
        });
    }
}

package com.djonce.sample.presenter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import com.djonce.sample.MainApplication;
import com.djonce.sample.model.api.Factory;
import com.donce.common.presenter.FileDownBasePresenter;
import com.donce.common.presenter.UpdateDownloadView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文件下载
 * Created by Administrator on 2016/8/10 0010.
 */
public class FileDownloadPresenter extends FileDownBasePresenter {

    private Call<ResponseBody> call;

    public FileDownloadPresenter(UpdateDownloadView view, String destFileDir, String destFileName) {
        super(view, destFileDir, destFileName);
    }

    //下载文件
    public void download(String url) {
        url = "http://down.360safe.com/360/inst.exe";
        call = Factory.provideHttpService().download(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String message;
                if (t == null) {
                    message = "";
                } else {
                    message = t.getMessage().toString();
                }
                getView().onFail(message);

            }
        });
    }

    //取消下载
    public void onCancelDownload() {
        if (call != null) {
            call.cancel();
        }
    }

}

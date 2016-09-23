package com.djonce.sample.presenter;


import android.os.Environment;

import com.djonce.sample.model.api.Factory;
import com.donce.common.callback.FileCallback;
import com.donce.common.presenter.DownloadView;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * 文件下载
 * Created by Administrator on 2016/8/10 0010.
 */
public class FileDownloadPresenter {

    private Call<ResponseBody> call;
    private DownloadView loadView;

    public FileDownloadPresenter(DownloadView view) {
        this.loadView = view;
    }

    //下载文件
    public void download(String url) {
        url = "http://down.360safe.com/360/inst.exe";
        String tag = "basic";
        String destFileDir = Environment.getExternalStorageDirectory().getPath() + "/" + tag + "/";
        String destFileName = System.currentTimeMillis() + "test123.exe";
        call = Factory.provideHttpService().download(url);
        call.enqueue(new FileCallback(destFileDir, destFileName) {

            @Override
            public void inProgress(float progress, long total) {
                loadView.inProgress(progress, total);
            }

            @Override
            public void onFailure(String msg) {
                loadView.onFailure(msg);
            }

            @Override
            public void onSuccess(File response) {
                if (response == null) {
                    loadView.onFailure("文件下载失败");
                    return;
                }
                loadView.onSuccess(response);
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

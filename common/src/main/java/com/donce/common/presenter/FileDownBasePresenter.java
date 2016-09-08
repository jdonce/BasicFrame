package com.donce.common.presenter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 文件下载的Presenter基类
 * Created by Administrator on 2016/8/11 0011.
 */
public class FileDownBasePresenter {

    private UpdateDownloadView updateDownloadView;

    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;

    public FileDownBasePresenter(UpdateDownloadView view, String destFileDir, String destFileName) {
        this.updateDownloadView = view;
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }


    //处理响应成功与失败后的数据
    public void handleResponse(Response<ResponseBody> response) {
        ResponseBody body = response.body();
        if (body == null) {
            onLoadFailure("文件异常");
            return;
        }
        try {
            File file = saveFile(response.body());
            updateDownloadView.onSuccess(file);
        } catch (IOException e) {
            e.printStackTrace();
            String message;
            if (e == null || TextUtils.isEmpty(e.getMessage())) {
                message = "";
            } else {
                message = e.getMessage();
            }
            onLoadFailure(message);
        }
    }

    //保存文件
    public File saveFile(ResponseBody response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.byteStream();
            final long total = response.contentLength();
            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        updateDownloadView.inProgress(finalSum * 1.0f / total, total);
                    }
                });

            }
            fos.flush();

            return file;

        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }

        }
    }

    public void onLoadFailure(String msg) {
        updateDownloadView.onFail(msg);
    }
}

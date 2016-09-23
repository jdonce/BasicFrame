package com.donce.common.callback;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文件下载的回调
 * Created by Administrator on 2016/9/22 0022.
 */
public abstract class FileCallback extends BaseCallback<File> implements Callback<ResponseBody> {

    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;


    public FileCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;

    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        onComplete();
        if (response == null || response.body() == null) {
            onFailure("文件异常");
            return;
        }
        try {
            File file = saveFile(response.body());
            onSuccess(file);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("fileCallback", e.getMessage());
            onFailure("文件下载失败");
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.d("fileCallback", t.getMessage());
        onFailure("文件下载失败");
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
                        //当大小未知时 total为-1
                        inProgress(finalSum * 1.0f / total, total);
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

}

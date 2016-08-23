package com.donce.common.presenter;

import java.io.File;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public interface UpdateDownloadView {
    void inProgress(float progress, long total);

    void onSuccess(File file);

    void onFail(String message);
}

package com.djonce.sample.ui.fragment;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.djonce.sample.R;
import com.djonce.sample.presenter.FileDownloadPresenter;
import com.djonce.sample.presenter.FileUploadPresenter;
import com.donce.common.presenter.UpdateDownloadView;
import com.donce.common.ui.BaseFragment;
import com.donce.common.util.SimpleAlertDialogHelper;
import com.donce.common.util.ToastUtil;
import com.donce.common.widget.SimpleAlertDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class ThirdFragment extends BaseFragment implements UpdateDownloadView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn1)
    Button btn1;
    private SimpleAlertDialog dialog;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_third;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText("TAB3");
    }


    @OnClick({R.id.btn1/*, R.id.btn2*/})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1://下载
                String tag = "basic";
                String destFileDir = Environment.getExternalStorageDirectory().getPath() + "/" + tag + "/";
                String destFileName = System.currentTimeMillis() + "test123.exe";
                final FileDownloadPresenter fileDownloadPresenter = new FileDownloadPresenter(this, destFileDir, destFileName);
                fileDownloadPresenter.download("url");

                dialog = SimpleAlertDialogHelper.showAlertDialog(getActivity(), "下载", "正在下载...", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fileDownloadPresenter.onCancelDownload();
                    }
                });
                break;
           /* case R.id.btn2://上传
                String file1 = Environment.getExternalStorageDirectory() + "/" + "1.txt";
                String file2 = Environment.getExternalStorageDirectory() + "/" + "2.txt";
                File f = new File(file1);
                File f2 = new File(file2);
                if (!f.exists() || !f2.exists()) {
                    ToastUtil.showLongToast(getActivity(), "文件不存在");
                    return;
                }
                String[] filePaths = new String[]{file1};
                FileUploadPresenter fileUploadPresenter = new FileUploadPresenter();
                fileUploadPresenter.upload(filePaths);
                break;*/
        }

    }

    @Override
    public void inProgress(float progress, long total) {
        int progressCount = (int) (100 * progress);
        Log.d("inProgress", progressCount + "%");
        dialog.setMessage(progressCount + "%");
    }

    @Override
    public void onSuccess(File file) {
        dialog.dismiss();
        ToastUtil.showLongToast(getActivity(), "成功");

    }

    @Override
    public void onFail(String message) {
        dialog.dismiss();
        ToastUtil.showLongToast(getActivity(), "失败");
    }
}

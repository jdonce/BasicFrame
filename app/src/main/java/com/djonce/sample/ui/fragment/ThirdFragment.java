package com.djonce.sample.ui.fragment;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.djonce.sample.R;
import com.djonce.sample.presenter.FileDownloadPresenter;
import com.donce.common.presenter.UpdateDownloadView;
import com.donce.common.ui.BaseFragment;
import com.donce.common.util.ToastUtil;
import com.donce.common.widget.Dialog.CustomFragmentDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class ThirdFragment extends BaseFragment implements UpdateDownloadView {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private CustomFragmentDialog dialog;
    private FileDownloadPresenter fileDownloadPresenter;

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
                showDialog();
                String tag = "basic";
                String destFileDir = Environment.getExternalStorageDirectory().getPath() + "/" + tag + "/";
                String destFileName = System.currentTimeMillis() + "test123.exe";
                fileDownloadPresenter = new FileDownloadPresenter(this, destFileDir, destFileName);
                fileDownloadPresenter.download("url");
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

    private void showDialog() {
        CustomFragmentDialog.Builder builder = new CustomFragmentDialog.Builder("下载", "0%", new String[]{"取消"});
        builder.setOnItemButtonClickListener(new CustomFragmentDialog.OnItemButtonClickListener() {
            @Override
            public void onItemClick(Object object, int position) {
                if (fileDownloadPresenter != null) {
                    fileDownloadPresenter.onCancelDownload();
                }
            }
        });
        dialog = builder.create();
        dialog.show(getFragmentManager(), "alert");
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
